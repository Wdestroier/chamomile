package com.github.wdestroier.chamomile.instruction.control;

import com.github.wdestroier.chamomile.instruction.Instruction;

public interface AbstractControlInstruction<T extends AbstractControlInstruction<T>> extends Instruction<T> {}
