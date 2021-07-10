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
public class ModulePackagesAttribute implements PseudoStructure<ModulePackagesAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int packageCount;
	private int[] packageIndex;

	@Override
	public ModulePackagesAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		packageCount = input.readUnsignedShort();
		packageIndex = new int[packageCount];

		for (var i = 0; i < packageCount; i++) {
			packageIndex[i] = input.readUnsignedShort();
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(packageCount);

		for (var i : packageIndex) {
			output.writeUnsignedShort(i);
		}
	}

}
