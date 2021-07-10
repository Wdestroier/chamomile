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
public class Exports implements PseudoStructure<Exports> {

	private int exportsIndex;
	private int exportsFlags;
	private int exportsToCount;
	private int[] exportsToIndex;

	@Override
	public Exports read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		exportsIndex = input.readUnsignedShort();
		exportsFlags = input.readUnsignedShort();
		exportsToCount = input.readUnsignedShort();
		exportsToIndex = new int[exportsToCount];

		for (var i = 0; i < exportsToCount; i++) {
			exportsToIndex[i] = input.readUnsignedShort();
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(exportsIndex);
		output.writeUnsignedShort(exportsFlags);
		output.writeUnsignedShort(exportsToCount);

		for (var eti : exportsToIndex) {
			output.writeUnsignedShort(eti);
		}
	}

}
