package com.github.wdestroier.chamomile.classfile.attributeinfo;

import static java.util.Map.entry;

import java.util.Map;
import java.util.function.BiFunction;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantUtf8;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class AttributeInfoFactory {

	private PseudoStructureFactory pseudoStructureFactory;
	private ClassFile classFile;

	private final Map<String, BiFunction<MultiEndianInputStream, PseudoStructureFactory, PseudoStructure<?>>> attributeInfoByName = Map.ofEntries(
			entry("ConstantValue", (t, u) -> new ConstantValueAttribute().read(t, u)),
			entry("Code", (t, u) -> new CodeAttribute().read(t, u)),
			entry("StackMapTable", (t, u) -> new StackMapTableAttribute().read(t, u)),
			entry("Exceptions", (t, u) -> new ExceptionsAttribute().read(t, u)),
			entry("InnerClasses", (t, u) -> new InnerClassesAttribute().read(t, u)),
			entry("EnclosingMethod", (t, u) -> new EnclosingMethodAttribute().read(t, u)),
			entry("Synthetic", (t, u) -> new SyntheticAttribute().read(t, u)),
			entry("Signature", (t, u) -> new SignatureAttribute().read(t, u)),
			entry("SourceFile", (t, u) -> new SourceFileAttribute().read(t, u)),
			entry("SourceDebugExtension", (t, u) -> new SourceDebugExtensionAttribute().read(t, u)),
			entry("LineNumberTable", (t, u) -> new LineNumberTableAttribute().read(t, u)),
			entry("LocalVariableTable", (t, u) -> new LocalVariableTableAttribute().read(t, u)),
			entry("LocalVariableTypeTable", (t, u) -> new LocalVariableTypeTableAttribute().read(t, u)),
			entry("Deprecated", (t, u) -> new DeprecatedAttribute().read(t, u)),
			entry("RuntimeVisibleAnnotations", (t, u) -> new RuntimeVisibleAnnotationsAttribute().read(t, u)),
			entry("RuntimeInvisibleAnnotations", (t, u) -> new RuntimeInvisibleAnnotationsAttribute().read(t, u)),
			entry("RuntimeVisibleParameterAnnotations", (t, u) -> new RuntimeVisibleParameterAnnotationsAttribute().read(t, u)),
			entry("RuntimeInvisibleParameterAnnotations", (t, u) -> new RuntimeInvisibleParameterAnnotationsAttribute().read(t, u)),
			entry("RuntimeVisibleTypeAnnotations", (t, u) -> new RuntimeVisibleTypeAnnotationsAttribute().read(t, u)),
			entry("RuntimeInvisibleTypeAnnotations", (t, u) -> new RuntimeInvisibleTypeAnnotationsAttribute().read(t, u)),
			entry("AnnotationDefault", (t, u) -> new AnnotationDefaultAttribute().read(t, u)),
			entry("BootstrapMethods", (t, u) -> new BootstrapMethodsAttribute().read(t, u)),
			entry("MethodParameters", (t, u) -> new MethodParametersAttribute().read(t, u)),
			entry("Module", (t, u) -> new ModuleAttribute().read(t, u)),
			entry("ModulePackages", (t, u) -> new ModulePackagesAttribute().read(t, u)),
			entry("ModuleMainClass", (t, u) -> new ModuleMainClassAttribute().read(t, u)),
			entry("NestHost", (t, u) -> new NestHostAttribute().read(t, u)),
			entry("NestMembers", (t, u) -> new NestMembersAttribute().read(t, u)));

	@SneakyThrows
	public PseudoStructure<?> getAttributeInfo(MultiEndianInputStream input) {
		return attributeInfoByName.getOrDefault(getAttributeName(input),
				(t, u) -> new AttributeInfo().read(t, u)).apply(input, pseudoStructureFactory);
	}

	@SneakyThrows
	private String getAttributeName(MultiEndianInputStream input) {
		// TODO The attributes need to be read in a better way to guarantee
		// that the class will be read successfully. The problem lives in
		// how to read an attribute and assign it to a class safely.

		//if (Boolean.TRUE) { return ""; }

		var attributeNameIndex = input.readUnsignedShort();
		input.unreadUnsignedShort(attributeNameIndex);

		String attributeName = "";

		var constantPoolLength = classFile.getConstantPool().length;
		if (attributeNameIndex >= 0 && attributeNameIndex <= constantPoolLength) {
			var attributeNameConstant = classFile.getConstantPool()[attributeNameIndex];
			if (attributeNameConstant instanceof ConstantUtf8) {
				var attributeNameUtf8 = (ConstantUtf8) attributeNameConstant;
				attributeName = attributeNameUtf8.getBytesString();
			}
		}

		return attributeName;
	}

}
