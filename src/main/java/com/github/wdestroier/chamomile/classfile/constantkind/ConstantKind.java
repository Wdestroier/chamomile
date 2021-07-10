package com.github.wdestroier.chamomile.classfile.constantkind;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;

public interface ConstantKind<T extends ConstantKind<T>> extends PseudoStructure<T> {

	short getTag();

}
