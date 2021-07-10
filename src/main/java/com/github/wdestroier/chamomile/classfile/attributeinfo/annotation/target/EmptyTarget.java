package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.target;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmptyTarget implements PseudoStructure<EmptyTarget> {

	@Override
	public EmptyTarget read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {}

}
