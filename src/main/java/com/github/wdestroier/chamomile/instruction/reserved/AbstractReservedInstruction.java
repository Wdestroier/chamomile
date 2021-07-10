package com.github.wdestroier.chamomile.instruction.reserved;

import com.github.wdestroier.chamomile.instruction.Instruction;

public interface AbstractReservedInstruction<T extends AbstractReservedInstruction<T>> extends Instruction<T> {}
