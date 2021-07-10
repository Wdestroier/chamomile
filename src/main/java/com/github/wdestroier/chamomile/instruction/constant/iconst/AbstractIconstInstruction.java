package com.github.wdestroier.chamomile.instruction.constant.iconst;

import com.github.wdestroier.chamomile.instruction.constant.AbstractConstantInstruction;

public interface AbstractIconstInstruction<T extends AbstractIconstInstruction<T>> extends AbstractConstantInstruction<T> {

	int getConstant();

}
