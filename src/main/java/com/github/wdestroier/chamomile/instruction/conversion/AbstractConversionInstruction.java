package com.github.wdestroier.chamomile.instruction.conversion;

import com.github.wdestroier.chamomile.instruction.Instruction;

public interface AbstractConversionInstruction<T extends AbstractConversionInstruction<T>> extends Instruction<T> {}
