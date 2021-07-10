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
public class TypeParameterBoundTarget implements PseudoStructure<TypeParameterBoundTarget> {

	private short typeParameterIndex;
	private short boundIndex;

	@Override
	public TypeParameterBoundTarget read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		typeParameterIndex = input.readUnsignedByte();
		boundIndex = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(typeParameterIndex);
		output.writeUnsignedByte(boundIndex);
	}

}
