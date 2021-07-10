package com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype;

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
public class ChopFrame implements PseudoStructure<ChopFrame> {

	private short frameType;
	private int offsetDelta;

	@Override
	public ChopFrame read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		frameType = input.readUnsignedByte();
		offsetDelta = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(frameType);
		output.writeUnsignedShort(offsetDelta);
	}

}
