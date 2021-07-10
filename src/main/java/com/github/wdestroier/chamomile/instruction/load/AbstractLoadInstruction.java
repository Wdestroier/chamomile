package com.github.wdestroier.chamomile.instruction.load;

import com.github.wdestroier.chamomile.instruction.Instruction;

public interface AbstractLoadInstruction<T extends AbstractLoadInstruction<T>> extends Instruction<T> {}
