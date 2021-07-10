package com.github.wdestroier.chamomile.instruction.store.lstore;

import com.github.wdestroier.chamomile.instruction.store.AbstractStoreInstruction;

public interface AbstractLstoreInstruction<T extends AbstractLstoreInstruction<T>> extends AbstractStoreInstruction<T> {

	short getIndex();

}
