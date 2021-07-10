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
public class FormalParameterTarget implements PseudoStructure<FormalParameterTarget> {

	private short formalParameterIndex;

	@Override
	public FormalParameterTarget read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		formalParameterIndex = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(formalParameterIndex);
	}

}
