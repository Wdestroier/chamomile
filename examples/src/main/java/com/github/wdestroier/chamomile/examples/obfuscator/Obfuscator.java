package com.github.wdestroier.chamomile.examples.obfuscator;

import java.io.File;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantClass;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantUtf8;
import com.github.wdestroier.chamomile.examples.obfuscator.transformers.EncapsulationTransformer;
import com.github.wdestroier.chamomile.examples.obfuscator.transformers.HideTransformer;
import com.github.wdestroier.chamomile.examples.obfuscator.transformers.ImmutabilityTransformer;
import com.github.wdestroier.chamomile.examples.obfuscator.transformers.ShuffleTransformer;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.SneakyThrows;

public class Obfuscator {

	private EncapsulationTransformer encapsulationTransformer = new EncapsulationTransformer();
	private HideTransformer hideTransformer = new HideTransformer();
	private ImmutabilityTransformer immutabilityTransformer = new ImmutabilityTransformer();
	private ShuffleTransformer shuffleTransformer = new ShuffleTransformer();

	public static void main(String... args) {
		var sampleClass = Obfuscator.class.getResourceAsStream("/CommonBinaryParser.class.example");

		if (sampleClass == null) {
			System.out.println("Could not find class in the resources folder!");
			System.exit(-1);
		}

		var input = new MultiEndianInputStream(sampleClass, Endianness.BIG_ENDIAN);
		var classFile = new ClassFile().read(input);

		var obfuscator = new Obfuscator();
		obfuscator.transform(classFile);

		saveClass(classFile);
	}

	public void transform(ClassFile classFile) {
		encapsulationTransformer.apply(classFile);
		hideTransformer.apply(classFile);
		immutabilityTransformer.apply(classFile);
		shuffleTransformer.apply(classFile);
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

		System.out.println("Saved class file at: " + file.getAbsolutePath());
	}

}
