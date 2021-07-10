package com.github.wdestroier.chamomile.instruction.store.istore;

import com.github.wdestroier.chamomile.instruction.store.AbstractStoreInstruction;

public interface AbstractIstoreInstruction<T extends AbstractIstoreInstruction<T>> extends AbstractStoreInstruction<T> {

	short getIndex();

}
