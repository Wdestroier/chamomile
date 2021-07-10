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
public class ConstantMethodType implements ConstantKind<ConstantMethodType> {

	private short tag;
	private int descriptorIndex;

	@Override
	public ConstantMethodType read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tag = input.readUnsignedByte();
		descriptorIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(tag);
		output.writeUnsignedShort(descriptorIndex);
	}

}
