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
public class ConstantInteger implements ConstantKind<ConstantInteger> {

	private short tag;
	private short[] bytes;

	@Override
	public ConstantInteger read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tag = input.readUnsignedByte();
		bytes = input.readUnsignedBytes(4);

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(tag);
		output.writeUnsignedBytes(bytes);
	}

}
