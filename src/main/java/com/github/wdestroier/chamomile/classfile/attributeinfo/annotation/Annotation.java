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
public class Annotation implements PseudoStructure<Annotation> {

	private int typeIndex;
	private int numElementValuePairs;
	private ElementValuePair[] elementValuePairs;

	@Override
	public Annotation read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		typeIndex = input.readUnsignedShort();
		numElementValuePairs = input.readUnsignedShort();
		elementValuePairs = new ElementValuePair[numElementValuePairs];

		for (var i = 0; i < numElementValuePairs; i++) {
			elementValuePairs[i] = new ElementValuePair().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(typeIndex);
		output.writeUnsignedShort(numElementValuePairs);

		for (var elementValuePair : elementValuePairs) {
			elementValuePair.write(output);
		}
	}

}
