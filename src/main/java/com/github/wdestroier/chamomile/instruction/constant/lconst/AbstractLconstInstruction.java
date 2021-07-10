package com.github.wdestroier.chamomile.instruction.constant.lconst;

import com.github.wdestroier.chamomile.instruction.constant.AbstractConstantInstruction;

public interface AbstractLconstInstruction<T extends AbstractLconstInstruction<T>> extends AbstractConstantInstruction<T> {

	long getConstant();

}
