package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
@AllArgsConstructor
public class ValueFactory {

	private PseudoStructureFactory pseudoStructureFactory;

	//TODO Should this factory be removed and put in the Value#read() method?
	@SneakyThrows
	public Value getValue(MultiEndianInputStream input) {
		var value = new Value();
		var tag = input.readUnsignedByte();

		switch (tag) {
		case 'B':
		case 'C':
		case 'D':
		case 'F':
		case 'I':
		case 'J':
		case 'S':
		case 'Z':
		case 's':
			var constValueIndex = input.readUnsignedShort();
			value.setConstValueIndex(constValueIndex);
			break;
		case 'e':
			var enumConstValue = new EnumConstValue().read(input, pseudoStructureFactory);
			value.setEnumConstValue(enumConstValue);
			break;
		case 'c':
			var classInfoIndex = input.readUnsignedShort();
			value.setClassInfoIndex(classInfoIndex);
			break;
		case '@':
			var annotationValue = new Annotation().read(input, pseudoStructureFactory); // I guess the factory can't be null
			value.setAnnotationValue(annotationValue);
			break;
		case '[':
			var arrayValue = new ArrayValue().read(input, pseudoStructureFactory);
			value.setArrayValue(arrayValue);
			break;
		default:
			// Should it return a Value with no field set?
			value = null;
			break;
		}

		return value;
	}

}
