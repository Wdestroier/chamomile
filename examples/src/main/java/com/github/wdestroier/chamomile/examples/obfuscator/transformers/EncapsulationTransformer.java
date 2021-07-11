package com.github.wdestroier.chamomile.examples.obfuscator.transformers;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.flag.FieldFlag;
import com.github.wdestroier.chamomile.classfile.flag.MethodFlag;
import com.github.wdestroier.chamomile.classfile.flag.NestedClassFlag;

public class EncapsulationTransformer extends Transformer {

	@Override
	public void apply(ClassFile classFile) {
		//Remove the private and protected flags from nested classes
		classFile.setAccessFlags(classFile.getAccessFlags() & NestedClassFlag.ACC_PUBLIC
				& ~NestedClassFlag.ACC_PRIVATE & ~NestedClassFlag.ACC_PROTECTED);

		//Remove the protected and final flags from all methods
		for (var method : classFile.getMethods()) {
			method.setAccessFlags(method.getAccessFlags() & MethodFlag.ACC_PUBLIC
					& ~MethodFlag.ACC_PROTECTED & ~MethodFlag.ACC_PRIVATE);
		}

		//Remove the protected and final flags from all fields
		for (var field : classFile.getFields()) {
			field.setAccessFlags(field.getAccessFlags() & FieldFlag.ACC_PUBLIC
					& ~FieldFlag.ACC_PROTECTED & FieldFlag.ACC_FINAL);
		}
	}

}
