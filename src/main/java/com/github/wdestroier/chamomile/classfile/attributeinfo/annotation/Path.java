package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation;

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
public class Path implements PseudoStructure<Path> {

	private short typePathKind;
	private short typeArgumentIndex;

	@Override
	public Path read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		typePathKind = input.readUnsignedByte();
		typeArgumentIndex = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(typePathKind);
		output.writeUnsignedByte(typeArgumentIndex);
	}

}
