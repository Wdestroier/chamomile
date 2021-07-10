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
public class AttributeInfo implements PseudoStructure<AttributeInfo> {

	private int attributeNameIndex;
	private long attributeLength;
	private short[] info;

	@Override
	public AttributeInfo read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		info = input.readUnsignedBytes((int) attributeLength);

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedBytes(info);
	}

}
