package com.github.wdestroier.chamomile.examples.obfuscator.transformers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantUtf8;

public abstract class Transformer {

	public abstract void apply(ClassFile classFile);

	protected <T> LinkedList<T> toLinkedList(T[] t) {
		return new LinkedList<T>(Arrays.asList(t));
	}

	protected <T> Stream<T> asStream(T[] t) {
		return Arrays.stream(t);
	}

	protected String toString(ClassFile classFile, int constantUtf8Index) {
		var constantUtf8 = (ConstantUtf8) classFile.getConstantPool()[constantUtf8Index];
		return constantUtf8.getBytesString();
	}

}
