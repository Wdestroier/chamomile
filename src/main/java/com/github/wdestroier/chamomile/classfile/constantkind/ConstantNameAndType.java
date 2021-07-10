package com.github.wdestroier.chamomile.classfile.constantkind;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstantNameAndType implements ConstantKind<ConstantNameAndType> {

	private short tag;
	private int nameIndex;
	private int descriptorIndex;

	@Override
	public ConstantNameAndType read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tag = input.readUnsignedByte();
		nameIndex = input.readUnsignedShort();
		descriptorIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(tag);
		output.writeUnsignedShort(nameIndex);
		output.writeUnsignedShort(descriptorIndex);
	}

}
