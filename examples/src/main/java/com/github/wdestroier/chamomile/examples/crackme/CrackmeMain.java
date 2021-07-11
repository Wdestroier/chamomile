package com.github.wdestroier.chamomile.examples.crackme;

import java.io.File;

import com.github.wdestroier.chamomile.LinearSweepDisassembler;
import com.github.wdestroier.chamomile.SinglePassAssembler;
import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.MethodInfo;
import com.github.wdestroier.chamomile.classfile.attributeinfo.CodeAttribute;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantClass;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantUtf8;
import com.github.wdestroier.chamomile.examples.dumper.DumperMain;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpeqInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpneInstruction;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.SneakyThrows;

public class CrackmeMain {

	public static void main(String... args) {
		// CrackMe1.jar was downloaded from http://crackmes.one/crackme/5f0c333633c5d42a7c6679b1
		// Thanks ohwhite for creating this simple crackme!

		// I unpacked the class file to not need to read the jar file contents
		var crackme = DumperMain.class.getResourceAsStream("/eahUaRpTUmfhN.class.crackme");

		if (crackme == null) {
			System.out.println("The crackme was not found in the resources folder");
			System.exit(-1);
		}

		// You can analyze the crackme by yourself...
		// Here you will understand how to load, modify and save class files

		// The JVM encodes everything in big endian
		var input = new MultiEndianInputStream(crackme, Endianness.BIG_ENDIAN);

		var classFile = new ClassFile();
		classFile.read(input);

		// The crackme's key verification is in the *main* method
		MethodInfo mainMethod = null;

		// Iterate over all class methods to find the main method
		for (var method : classFile.getMethods()) {
			// To get the method name we need to grab the name index
			// and then lookup in the constant pool
			var nameIndex = method.getNameIndex();
			var methodName = ((ConstantUtf8) classFile.getConstantPool()[nameIndex]).getBytesString();

			if ("main".equals(methodName)) {
				mainMethod = method;
				// Nesting for loops will make the code harder to read

				break;
			}
		}

		if (mainMethod == null) {
			System.out.println("The main method could not be found!\n"
					+ "You are out of luck.");
			System.exit(-1);
		}

		CodeAttribute codeAttribute = null;

		// Now grab the method bytecode, it is stored in the Code attribute
		// Technically, every method must have only 1 Code attribute to be a
		// valid class file and be loaded by the JVM
		for (var attribute : mainMethod.getAttributes()) {
			if (attribute instanceof CodeAttribute) {
				codeAttribute = (CodeAttribute) attribute;

				break;
			}
		}

		if (codeAttribute == null) {
			System.out.println("The main method Code attribute could not be found!\n"
					+ "The crackme file is probably corrupted.");
			System.exit(-1);
		}

		var bytecode = codeAttribute.getCode();

		// Initialize the disassembler and read the instructions
		var disassembler = new LinearSweepDisassembler();

		var instructions = disassembler.disassemble(bytecode);

		IfIcmpeqInstruction keyCompareInstruction = null;

		// By analyzing the crackme you can find that the *if_icmpeq* instruction
		// is comparing the key and there is no other use of this instruction
		// That means I can replace the first or all occurrences
		for (var instruction : instructions) {
			if (instruction instanceof IfIcmpeqInstruction) {
				keyCompareInstruction = (IfIcmpeqInstruction) instruction;
			}
		}

		if (keyCompareInstruction == null) {
			System.out.println("Call the ambulance");
			System.exit(-1);
		}

		// The old instruction if_icmpeq (if integer comparison equal) is
		// equivalent to if (a == b)
		// Replace it with if_icmpne (if integer comparison not equal)
		// Which is equivalent to if (a != b)
		var newInstruction = new IfIcmpneInstruction();
		newInstruction.setBranchOffset(keyCompareInstruction.getBranchOffset());

		var i = instructions.indexOf(keyCompareInstruction);
		instructions.set(i, newInstruction);

		// Patch the bytecode and save the class file
		var assembler = new SinglePassAssembler();

		var patchedBytecode = assembler.assemble(instructions);
		codeAttribute.setCode(patchedBytecode);

		// Save the class file

		saveClass(classFile);

		// To run the crackme you'll need to replace the patched class file in
		// the crackme's jar file. You can do it either manually or programmatically
		// It will work, but test by yourself too :)
	}

	@SneakyThrows
	private static void saveClass(ClassFile classFile) {
		// Grab the internal class name from the constant pool
		var classNameIndex = ((ConstantClass) classFile.getConstantPool()[classFile.getThisClass()]).getNameIndex();
		var internalClassName = ((ConstantUtf8) classFile.getConstantPool()[classNameIndex]).getBytesString();

		// The internal class name will be in the format com/example/project/Main
		var splitName = internalClassName.split("/");
		var packageCount = splitName.length - 1;

		var className = splitName[packageCount];

		// Save the file in the temp folder
		var file = new File(System.getProperty("java.io.tmpdir"), className + ".class");
		file.createNewFile();

		classFile.write(file);

		System.out.println("Saved patched class file at: " + file.getAbsolutePath());
	}

}
