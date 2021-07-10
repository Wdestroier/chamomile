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
public class ElementValuePair implements PseudoStructure<ElementValuePair> {

	private int elementNameIndex;
	private ElementValue value;

	@Override
	public ElementValuePair read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		elementNameIndex = input.readUnsignedShort();
		value = new ElementValue().read(input, pseudoStructureFactory);

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(elementNameIndex);
		value.write(output);
	}

}
