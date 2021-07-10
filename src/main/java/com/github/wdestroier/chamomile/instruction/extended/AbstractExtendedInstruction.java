package com.github.wdestroier.chamomile.instruction.extended;

import com.github.wdestroier.chamomile.instruction.Instruction;

public interface AbstractExtendedInstruction<T extends AbstractExtendedInstruction<T>> extends Instruction<T> {}
