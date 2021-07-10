package com.github.wdestroier.chamomile.instruction.load.dload;

import com.github.wdestroier.chamomile.instruction.load.AbstractLoadInstruction;

public interface AbstractDloadInstruction<T extends AbstractDloadInstruction<T>> extends AbstractLoadInstruction<T> {

	short getIndex();

}
