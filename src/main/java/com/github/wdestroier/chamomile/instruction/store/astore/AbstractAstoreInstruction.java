package com.github.wdestroier.chamomile.instruction.store.astore;

import com.github.wdestroier.chamomile.instruction.store.AbstractStoreInstruction;

public interface AbstractAstoreInstruction<T extends AbstractAstoreInstruction<T>> extends AbstractStoreInstruction<T> {

	short getIndex();

}
