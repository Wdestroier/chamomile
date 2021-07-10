package com.github.wdestroier.chamomile.classfile.attributeinfo.module;

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
public class Opens implements PseudoStructure<Opens> {

	private int opensIndex;
	private int opensFlags;
	private int opensToCount;
	private int[] opensToIndex;

	@Override
	public Opens read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		opensIndex = input.readUnsignedShort();
		opensFlags = input.readUnsignedShort();
		opensToCount = input.readUnsignedShort();
		opensToIndex = new int[opensToCount];

		for (var i = 0; i < opensToCount; i++) {
			opensToIndex[i] = input.readUnsignedShort();
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(opensIndex);
		output.writeUnsignedShort(opensFlags);
		output.writeUnsignedShort(opensToCount);

		for (var oti : opensToIndex) {
			output.writeUnsignedShort(oti);
		}
	}

}
