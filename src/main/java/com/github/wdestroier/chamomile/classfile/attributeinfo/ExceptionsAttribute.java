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
public class ExceptionsAttribute implements PseudoStructure<ExceptionsAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int numberOfExceptions;
	private int[] exceptionIndexTable;

	@Override
	public ExceptionsAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numberOfExceptions = input.readUnsignedShort();
		exceptionIndexTable = new int[numberOfExceptions];

		for (var i = 0; i < numberOfExceptions; i++) {
			exceptionIndexTable[i] = input.readUnsignedShort();
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(numberOfExceptions);

		for (var exceptionIndex : exceptionIndexTable) {
			output.writeUnsignedShort(exceptionIndex);
		}
	}

}
