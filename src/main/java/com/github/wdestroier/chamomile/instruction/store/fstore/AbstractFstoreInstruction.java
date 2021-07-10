package com.github.wdestroier.chamomile.instruction.store.fstore;

import com.github.wdestroier.chamomile.instruction.store.AbstractStoreInstruction;

public interface AbstractFstoreInstruction<T extends AbstractFstoreInstruction<T>> extends AbstractStoreInstruction<T> {

	short getIndex();

}
