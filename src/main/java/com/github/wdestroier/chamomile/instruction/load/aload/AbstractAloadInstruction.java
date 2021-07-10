package com.github.wdestroier.chamomile.instruction.load.aload;

import com.github.wdestroier.chamomile.instruction.load.AbstractLoadInstruction;

public interface AbstractAloadInstruction<T extends AbstractAloadInstruction<T>> extends AbstractLoadInstruction<T> {

	short getIndex();

}
