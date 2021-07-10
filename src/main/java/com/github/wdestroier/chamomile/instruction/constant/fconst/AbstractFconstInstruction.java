package com.github.wdestroier.chamomile.instruction.constant.fconst;

import com.github.wdestroier.chamomile.instruction.constant.AbstractConstantInstruction;

public interface AbstractFconstInstruction<T extends AbstractFconstInstruction<T>> extends AbstractConstantInstruction<T> {

	float getConstant();

}
