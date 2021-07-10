package com.github.wdestroier.chamomile.instruction.store.dstore;

import com.github.wdestroier.chamomile.instruction.store.AbstractStoreInstruction;

public interface AbstractDstoreInstruction<T extends AbstractDstoreInstruction<T>> extends AbstractStoreInstruction<T> {

	short getIndex();

}
