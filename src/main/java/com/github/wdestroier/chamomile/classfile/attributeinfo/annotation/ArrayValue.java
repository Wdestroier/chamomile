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
public class ArrayValue implements PseudoStructure<ArrayValue> {

	private int numValues;
	private ElementValue[] values;

	@Override
	public ArrayValue read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		numValues = input.readUnsignedShort();
		values = new ElementValue[numValues];

		for (var i = 0; i < numValues; i++) {
			values[i] = new ElementValue().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(numValues);

		for (var value : values) {
			value.write(output);
		}
	}

}
