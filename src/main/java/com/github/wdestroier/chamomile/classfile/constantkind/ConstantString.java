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
public class ConstantString implements ConstantKind<ConstantString> {

	private short tag;
	private int stringIndex;

	@Override
	public ConstantString read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tag = input.readUnsignedByte();
		stringIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(tag);
		output.writeUnsignedShort(stringIndex);
	}

}
