package com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.verificationtype;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.Data;

@Data
public class VerificationTypeInfo implements PseudoStructure<VerificationTypeInfo> {

	private TopVariableInfo topVariableInfo;
	private IntegerVariableInfo integerVariableInfo;
	private FloatVariableInfo floatVariableInfo;
	private LongVariableInfo longVariableInfo;
	private DoubleVariableInfo doubleVariableInfo;
	private NullVariableInfo nullVariableInfo;
	private UninitializedThisVariableInfo uninitializedThisVariableInfo;
	private ObjectVariableInfo objectVariableInfo;
	private UninitializedVariableInfo uninitializedVariableInfo;

	@Override
	public VerificationTypeInfo read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		return pseudoStructureFactory.getVerificationTypeInfo(input);
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		if (topVariableInfo != null) {
			topVariableInfo.write(output);
		} else if (integerVariableInfo != null) {
			integerVariableInfo.write(output);
		} else if (floatVariableInfo != null) {
			floatVariableInfo.write(output);
		} else if (longVariableInfo != null) {
			longVariableInfo.write(output);
		} else if (doubleVariableInfo != null) {
			doubleVariableInfo.write(output);
		} else if (nullVariableInfo != null) {
			nullVariableInfo.write(output);
		} else if (uninitializedThisVariableInfo != null) {
			uninitializedThisVariableInfo.write(output);
		} else if (objectVariableInfo != null) {
			objectVariableInfo.write(output);
		} else if (uninitializedVariableInfo != null) {
			uninitializedVariableInfo.write(output);
		}
	}

	public VerificationTypeInfo setTopVariableInfo(TopVariableInfo topVariableInfo) {
		this.topVariableInfo = topVariableInfo;
		integerVariableInfo = null;
		floatVariableInfo = null;
		longVariableInfo = null;
		doubleVariableInfo = null;
		nullVariableInfo = null;
		uninitializedThisVariableInfo = null;
		objectVariableInfo = null;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setIntegerVariableInfo(IntegerVariableInfo integerVariableInfo) {
		topVariableInfo = null;
		this.integerVariableInfo = integerVariableInfo;
		floatVariableInfo = null;
		longVariableInfo = null;
		doubleVariableInfo = null;
		nullVariableInfo = null;
		uninitializedThisVariableInfo = null;
		objectVariableInfo = null;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setFloatVariableInfo(FloatVariableInfo floatVariableInfo) {
		topVariableInfo = null;
		integerVariableInfo = null;
		this.floatVariableInfo = floatVariableInfo;
		longVariableInfo = null;
		doubleVariableInfo = null;
		nullVariableInfo = null;
		uninitializedThisVariableInfo = null;
		objectVariableInfo = null;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setLongVariableInfo(LongVariableInfo longVariableInfo) {
		topVariableInfo = null;
		integerVariableInfo = null;
		floatVariableInfo = null;
		this.longVariableInfo = longVariableInfo;
		doubleVariableInfo = null;
		nullVariableInfo = null;
		uninitializedThisVariableInfo = null;
		objectVariableInfo = null;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setDoubleVariableInfo(DoubleVariableInfo doubleVariableInfo) {
		topVariableInfo = null;
		integerVariableInfo = null;
		floatVariableInfo = null;
		longVariableInfo = null;
		this.doubleVariableInfo = doubleVariableInfo;
		nullVariableInfo = null;
		uninitializedThisVariableInfo = null;
		objectVariableInfo = null;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setNullVariableInfo(NullVariableInfo nullVariableInfo) {
		topVariableInfo = null;
		integerVariableInfo = null;
		floatVariableInfo = null;
		longVariableInfo = null;
		doubleVariableInfo = null;
		this.nullVariableInfo = nullVariableInfo;
		uninitializedThisVariableInfo = null;
		objectVariableInfo = null;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setUninitializedThisVariableInfo(UninitializedThisVariableInfo uninitializedThisVariableInfo) {
		topVariableInfo = null;
		integerVariableInfo = null;
		floatVariableInfo = null;
		longVariableInfo = null;
		doubleVariableInfo = null;
		nullVariableInfo = null;
		this.uninitializedThisVariableInfo = uninitializedThisVariableInfo;
		objectVariableInfo = null;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setObjectVariableInfo(ObjectVariableInfo objectVariableInfo) {
		topVariableInfo = null;
		integerVariableInfo = null;
		floatVariableInfo = null;
		longVariableInfo = null;
		doubleVariableInfo = null;
		nullVariableInfo = null;
		uninitializedThisVariableInfo = null;
		this.objectVariableInfo = objectVariableInfo;
		uninitializedVariableInfo = null;

		return this;
	}

	public VerificationTypeInfo setUninitializedVariableInfo(UninitializedVariableInfo uninitializedVariableInfo) {
		topVariableInfo = null;
		integerVariableInfo = null;
		floatVariableInfo = null;
		longVariableInfo = null;
		doubleVariableInfo = null;
		nullVariableInfo = null;
		uninitializedThisVariableInfo = null;
		objectVariableInfo = null;
		this.uninitializedVariableInfo = uninitializedVariableInfo;

		return this;
	}

}
