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
public class Requires implements PseudoStructure<Requires> {

	private int requiresIndex;
	private int requiresFlags;
	private int requiresVersionIndex;

	@Override
	public Requires read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		requiresIndex = input.readUnsignedShort();
		requiresFlags = input.readUnsignedShort();
		requiresVersionIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(requiresIndex);
		output.writeUnsignedShort(requiresFlags);
		output.writeUnsignedShort(requiresVersionIndex);
	}

}
