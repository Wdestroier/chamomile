package com.github.wdestroier.chamomile.instruction.load.iload;

import com.github.wdestroier.chamomile.instruction.load.AbstractLoadInstruction;

public interface AbstractIloadInstruction<T extends AbstractIloadInstruction<T>> extends AbstractLoadInstruction<T> {

	short getIndex();

}
