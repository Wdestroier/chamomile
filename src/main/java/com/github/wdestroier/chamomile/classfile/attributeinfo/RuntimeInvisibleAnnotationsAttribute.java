package com.github.wdestroier.chamomile.classfile.attributeinfo;

import com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.Annotation;
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
public class RuntimeInvisibleAnnotationsAttribute implements PseudoStructure<RuntimeInvisibleAnnotationsAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int numAnnotations;
	private Annotation[] annotations;

	@Override
	public RuntimeInvisibleAnnotationsAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numAnnotations = input.readUnsignedShort();
		annotations = new Annotation[numAnnotations];

		for (var i = 0; i < numAnnotations; i++) {
			annotations[i] = new Annotation().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(numAnnotations);

		for (var annotation : annotations) {
			annotation.write(output);
		}
	}

}
