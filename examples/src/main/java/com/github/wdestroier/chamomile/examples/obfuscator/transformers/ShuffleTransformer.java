package com.github.wdestroier.chamomile.examples.obfuscator.transformers;

import java.util.Collections;
import java.util.Random;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.FieldInfo;
import com.github.wdestroier.chamomile.classfile.MethodInfo;

public class ShuffleTransformer extends Transformer {

	private Random random = new Random();

	@Override
	public void apply(ClassFile classFile) {
		//Shuffle fields
		var fields = toLinkedList(classFile.getFields());
		Collections.shuffle(fields, random);
		classFile.setFields(fields.toArray(new FieldInfo[fields.size()]));

		//Shuffle methods
		var methods = toLinkedList(classFile.getMethods());
		Collections.shuffle(methods, random);
		classFile.setMethods(methods.toArray(new MethodInfo[methods.size()]));
	}

}
