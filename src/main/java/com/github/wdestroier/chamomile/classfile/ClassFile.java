package com.github.wdestroier.chamomile.classfile;

import com.github.wdestroier.chamomile.classfile.attributeinfo.AttributeInfoFactory;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantDouble;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantLong;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassFile implements PseudoStructure<ClassFile> {

	private long magic;
	private int minorVersion;
	private int majorVersion;
	private int constantPoolCount;
	private PseudoStructure<?>[] constantPool;
	private int accessFlags;
	private int thisClass;
	private int superClass;
	private int interfacesCount;
	private int[] interfaces;
	private int fieldsCount;
	private FieldInfo[] fields;
	private int methodsCount;
	private MethodInfo[] methods;
	private int attributesCount;
	private PseudoStructure<?>[] attributes;

	@Override
	public ClassFile read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		//TODO ClassFileVerifier, MethodInfoVerifier ...

		magic = input.readUnsignedInt();
		minorVersion = input.readUnsignedShort();
		majorVersion = input.readUnsignedShort();
		constantPoolCount = input.readUnsignedShort();

		constantPool = new PseudoStructure<?>[constantPoolCount];

		/*
		 * cpInfo[0] is reserved
		 * 
		 * https://stackoverflow.com/questions/56808432/why-is-the-constant-pool-in-java-classfile-indexed-from-1-and-not-0-what-is#:~:text=%22%5BI%5Dndexed%20from%201,index%20into%20the%20constant%20pool.
		 * https://stackoverflow.com/questions/56808432/why-is-the-constant-pool-in-java-classfile-indexed-from-1-and-not-0-what-is#:~:text=2%20Answers&text=They%20skipped%20index%200%20so,%22catch%20all%22%20exception%20handlers.
		 */
		for (var i = 1; i < constantPoolCount; i++) {
			var constantKind = constantPool[i] = pseudoStructureFactory.getConstantKind(input);

			// All 8-byte constants take up two entries in the constant_pool
			if (constantKind instanceof ConstantLong || constantKind instanceof ConstantDouble) {
				constantPool[++i] = null;
			}
		}

		accessFlags = input.readUnsignedShort();

		/*
		 * Must be a valid index in the constant pool table to a ConstantClass structure,
		 * representing the class or interface defined by the class file
		 */
		thisClass = input.readUnsignedShort();
		superClass = input.readUnsignedShort();
		interfacesCount = input.readUnsignedShort();

		interfaces = new int[interfacesCount];

		for (var i = 0; i < interfacesCount; i++) {
			interfaces[i] = input.readUnsignedShort();
		}

		fieldsCount = input.readUnsignedShort();

		pseudoStructureFactory.setAttributeInfoFactory(
				new AttributeInfoFactory(pseudoStructureFactory, this));

		fields = new FieldInfo[fieldsCount];

		for (var i = 0; i < fieldsCount; i++) {
			fields[i] = new FieldInfo().read(input, pseudoStructureFactory);
		}

		methodsCount = input.readUnsignedShort();

		methods = new MethodInfo[methodsCount];

		for (var i = 0; i < methodsCount; i++) {
			methods[i] = new MethodInfo().read(input, pseudoStructureFactory);
		}

		attributesCount = input.readUnsignedShort();

		attributes = new PseudoStructure<?>[attributesCount];

		for (var i = 0; i < attributesCount; i++) {
			attributes[i] = pseudoStructureFactory.getAttributeInfo(input);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedInt(magic);
		output.writeUnsignedShort(minorVersion);
		output.writeUnsignedShort(majorVersion);
		output.writeUnsignedShort(constantPoolCount);

		for (var constant : constantPool) {
			if (constant != null) {
				constant.write(output);
			}
		}

		output.writeUnsignedShort(accessFlags);
		output.writeUnsignedShort(thisClass);
		output.writeUnsignedShort(superClass);
		output.writeUnsignedShort(interfacesCount);

		for (var i : interfaces) {
			output.writeUnsignedShort(i);
		}

		output.writeUnsignedShort(fieldsCount);

		for (var field : fields) {
			field.write(output);
		}

		output.writeUnsignedShort(methodsCount);

		for (var method : methods) {
			method.write(output);
		}

		output.writeUnsignedShort(attributesCount);

		for (var attribute : attributes) {
			if (attribute != null) {
				attribute.write(output);
			}
		}
	}

}
