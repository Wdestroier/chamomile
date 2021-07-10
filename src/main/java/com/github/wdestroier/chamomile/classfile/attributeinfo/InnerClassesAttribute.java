package com.github.wdestroier.chamomile.classfile.attributeinfo;

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
public class InnerClassesAttribute implements PseudoStructure<InnerClassesAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int numberOfClasses;
	private InnerClass[] classes;

	@Override
	public InnerClassesAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numberOfClasses = input.readUnsignedShort();
		classes = new InnerClass[numberOfClasses];

		for (var i = 0; i < numberOfClasses; i++) {
			classes[i] = new InnerClass().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(numberOfClasses);

		for (var innerClass : classes) {
			innerClass.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class InnerClass implements PseudoStructure<InnerClass> {

		private long innerClassInfoIndex;
		private int outerClassInfoIndex;
		private int innerNameIndex;
		private int innerClassAccessFlags;

		@Override
		public InnerClass read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			innerClassInfoIndex = input.readUnsignedInt();
			outerClassInfoIndex = input.readUnsignedShort();
			innerNameIndex = input.readUnsignedShort();
			innerClassAccessFlags = input.readUnsignedShort();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedInt(innerClassInfoIndex);
			output.writeUnsignedShort(outerClassInfoIndex);
			output.writeUnsignedShort(innerNameIndex);
			output.writeUnsignedShort(innerClassAccessFlags);
		}

	}

}
