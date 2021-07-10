package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterAnnotation implements PseudoStructure<ParameterAnnotation> {

	private int numAnnotations;
	private Annotation[] annotations;

	@Override
	public ParameterAnnotation read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		numAnnotations = input.readUnsignedShort();
		annotations = new Annotation[numAnnotations];

		for (var i = 0; i < numAnnotations; i++) {
			annotations[i] = new Annotation().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedInt(numAnnotations);

		for (var annotation : annotations) {
			annotation.write(output);
		}
	}

}
