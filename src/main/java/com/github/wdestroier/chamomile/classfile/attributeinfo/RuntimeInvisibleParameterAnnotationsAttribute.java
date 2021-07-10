package com.github.wdestroier.chamomile.classfile.attributeinfo;

import com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.ParameterAnnotation;
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
public class RuntimeInvisibleParameterAnnotationsAttribute implements PseudoStructure<RuntimeInvisibleParameterAnnotationsAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private short numParameters;
	private ParameterAnnotation[] parameterAnnotations;

	@Override
	public RuntimeInvisibleParameterAnnotationsAttribute read(MultiEndianInputStream input,
                                                              PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numParameters = input.readUnsignedByte();
		parameterAnnotations = new ParameterAnnotation[numParameters];

		for (var i = 0; i < numParameters; i++) {
			parameterAnnotations[i] = new ParameterAnnotation().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedByte(numParameters);

		for (var parameterAnnotation : parameterAnnotations) {
			parameterAnnotation.write(output);
		}
	}

}
