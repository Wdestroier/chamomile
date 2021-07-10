package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.Data;

/**
 * This class is an union type. Only 1 field is used at the time.
 */
@Data
public class Value implements PseudoStructure<Value> {

	/**
	 * Must be a valid index in the constant pool table. The entry at this index
	 * must be of a type appropriate to the tag. (String = ConstantUtf8, Integer =
	 * ConstantInteger ...)
	 */
	private Integer constValueIndex;

	private EnumConstValue enumConstValue;
	private Integer classInfoIndex;
	private Annotation annotationValue;
	private ArrayValue arrayValue;

	@Override
	public Value read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		return pseudoStructureFactory.getValue(input);
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		if (constValueIndex != null) {
			output.writeUnsignedShort(constValueIndex);
		} else if (enumConstValue != null) {
			enumConstValue.write(output);
		} else if (enumConstValue != null) {
			output.writeUnsignedShort(classInfoIndex);
		} else if (enumConstValue != null) {
			annotationValue.write(output);
		} else if (enumConstValue != null) {
			arrayValue.write(output);
		}
	}

	public Value setConstValueIndex(int constValueIndex) {
		this.constValueIndex = constValueIndex;
		enumConstValue = null;
		classInfoIndex = null;
		annotationValue = null;
		arrayValue = null;

		return this;
	}

	public Value setEnumConstValue(EnumConstValue enumConstValue) {
		constValueIndex = null;
		this.enumConstValue = enumConstValue;
		classInfoIndex = null;
		annotationValue = null;
		arrayValue = null;

		return this;
	}

	public Value setClassInfoIndex(int classInfoIndex) {
		constValueIndex = null;
		enumConstValue = null;
		this.classInfoIndex = classInfoIndex;
		annotationValue = null;
		arrayValue = null;

		return this;
	}

	public Value setAnnotationValue(Annotation annotationValue) {
		constValueIndex = null;
		enumConstValue = null;
		classInfoIndex = null;
		this.annotationValue = annotationValue;
		arrayValue = null;

		return this;
	}

	public Value setArrayValue(ArrayValue arrayValue) {
		constValueIndex = null;
		enumConstValue = null;
		classInfoIndex = null;
		annotationValue = null;
		this.arrayValue = arrayValue;

		return this;
	}

}
