package com.github.wdestroier.chamomile.examples.obfuscator.transformers;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.attributeinfo.MethodParametersAttribute;
import com.github.wdestroier.chamomile.classfile.flag.ClassFlag;
import com.github.wdestroier.chamomile.classfile.flag.FieldFlag;
import com.github.wdestroier.chamomile.classfile.flag.MethodFlag;
import com.github.wdestroier.chamomile.classfile.flag.ParameterFlag;

public class ImmutabilityTransformer extends Transformer {

	@Override
	public void apply(ClassFile classFile) {
		//Remove the final flag from the class or nested class
		classFile.setAccessFlags(classFile.getAccessFlags() & ~ClassFlag.ACC_FINAL);

		//Remove the final flag from all methods
		for (var method : classFile.getMethods()) {
			var methodName = toString(classFile, method.getNameIndex());

			if (!methodName.startsWith("<")) {
				method.setAccessFlags(method.getAccessFlags() & ~MethodFlag.ACC_FINAL);
			}

			//Find the MethodParameters attribute
			for (var attribute : method.getAttributes()) {
				if (attribute instanceof MethodParametersAttribute) {
					var methodParametersAttribute = (MethodParametersAttribute) attribute;

					//Add the synthetic flag to all method parameters
					for (var parameter : methodParametersAttribute.getParameters()) {
						parameter.setAccessFlags(parameter.getAccessFlags() & ~ParameterFlag.ACC_FINAL);
					}

					break;
				}
			}
		}

		//Remove the final flag from all fields
		for (var field : classFile.getFields()) {
			field.setAccessFlags(field.getAccessFlags() & ~FieldFlag.ACC_FINAL);
		}
	}

}
