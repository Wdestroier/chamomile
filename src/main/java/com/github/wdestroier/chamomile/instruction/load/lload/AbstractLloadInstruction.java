package com.github.wdestroier.chamomile.instruction.load.lload;

import com.github.wdestroier.chamomile.instruction.load.AbstractLoadInstruction;

public interface AbstractLloadInstruction<T extends AbstractLloadInstruction<T>> extends AbstractLoadInstruction<T> {

	short getIndex();

}
