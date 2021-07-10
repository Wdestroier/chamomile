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
public class Provides implements PseudoStructure<Provides> {

	private int providesIndex;
	private int providesWithCount;
	private int[] providesWithIndex;

	@Override
	public Provides read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		providesIndex = input.readUnsignedShort();
		providesWithCount = input.readUnsignedShort();
		providesWithIndex = new int[providesWithCount];

		for (var i = 0; i < providesWithCount; i++) {
			providesWithIndex[i] = input.readUnsignedShort();
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(providesIndex);
		output.writeUnsignedShort(providesWithCount);

		for (var pwi : providesWithIndex) {
			output.writeUnsignedShort(pwi);
		}
	}

}
