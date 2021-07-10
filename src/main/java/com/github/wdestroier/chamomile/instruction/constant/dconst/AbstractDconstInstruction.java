package com.github.wdestroier.chamomile.instruction.constant.dconst;

import com.github.wdestroier.chamomile.instruction.constant.AbstractConstantInstruction;

public interface AbstractDconstInstruction<T extends AbstractDconstInstruction<T>> extends AbstractConstantInstruction<T> {

	double getConstant();

}
