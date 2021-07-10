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
public class NestMembersAttribute implements PseudoStructure<NestMembersAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int numberOfClasses;
	private int[] classes;

	@Override
	public NestMembersAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numberOfClasses = input.readUnsignedShort();
		classes = new int[numberOfClasses];

		for (var i = 0; i < numberOfClasses; i++) {
			classes[i] = input.readUnsignedShort();
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(numberOfClasses);

		for (var c : classes) {
			output.writeUnsignedShort(c);
		}
	}

}
