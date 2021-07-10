package com.github.wdestroier.chamomile.instruction.stack;

import com.github.wdestroier.chamomile.instruction.Instruction;

public interface AbstractStackInstruction<T extends AbstractStackInstruction<T>> extends Instruction<T> {}
