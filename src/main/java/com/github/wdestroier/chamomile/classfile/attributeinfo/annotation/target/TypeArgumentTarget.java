package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.target;

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
public class TypeArgumentTarget implements PseudoStructure<TypeArgumentTarget> {

	private int offset;
	private short typeArgumentIndex;

	@Override
	public TypeArgumentTarget read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		offset = input.readUnsignedShort();
		typeArgumentIndex = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(offset);
		output.writeUnsignedByte(typeArgumentIndex);
	}

}
