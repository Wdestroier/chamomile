package com.github.wdestroier.chamomile.classfile.attributeinfo;

import com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.TypeAnnotation;
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
public class RuntimeVisibleTypeAnnotationsAttribute implements PseudoStructure<RuntimeVisibleTypeAnnotationsAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int numAnnotations;
	private TypeAnnotation[] annotations;

	@Override
	public RuntimeVisibleTypeAnnotationsAttribute read(MultiEndianInputStream input,
                                                       PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numAnnotations = input.readUnsignedShort();
		annotations = new TypeAnnotation[numAnnotations];

		for (var i = 0; i < numAnnotations; i++) {
			annotations[i] = new TypeAnnotation().read(input, pseudoStructureFactory);
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
