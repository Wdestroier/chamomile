package com.github.wdestroier.chamomile.instruction.load.fload;

import com.github.wdestroier.chamomile.instruction.load.AbstractLoadInstruction;

public interface AbstractFloadInstruction<T extends AbstractFloadInstruction<T>> extends AbstractLoadInstruction<T> {

	short getIndex();

}
