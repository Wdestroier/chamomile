package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.target;

import com.github.wdestroier.chamomile.classfile.pseudostructure.*;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffsetTarget implements PseudoStructure<OffsetTarget> {

	private int offset;

	@Override
	public OffsetTarget read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		offset = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(offset);
	}

}
