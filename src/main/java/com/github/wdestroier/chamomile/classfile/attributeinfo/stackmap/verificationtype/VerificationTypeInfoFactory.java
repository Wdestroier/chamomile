package com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.verificationtype;

import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VerificationTypeInfoFactory {

	private VariableInfoFactory variableInfoFactory;

	public VerificationTypeInfoFactory() {
		this(new VariableInfoFactory());
	}

	public VerificationTypeInfo getVerificationTypeInfo(MultiEndianInputStream data) {
		return getVerificationTypeInfo(variableInfoFactory.getVariableInfo(data));
	}

	public VerificationTypeInfo getVerificationTypeInfo(Object variableInfo) {
		VerificationTypeInfo verificationTypeInfo = new VerificationTypeInfo();

		if (variableInfo instanceof TopVariableInfo) {
			verificationTypeInfo.setTopVariableInfo((TopVariableInfo) variableInfo);
		} else if (variableInfo instanceof IntegerVariableInfo) {
			verificationTypeInfo.setIntegerVariableInfo((IntegerVariableInfo) variableInfo);
		} else if (variableInfo instanceof FloatVariableInfo) {
			verificationTypeInfo.setFloatVariableInfo((FloatVariableInfo) variableInfo);
		} else if (variableInfo instanceof LongVariableInfo) {
			verificationTypeInfo.setLongVariableInfo((LongVariableInfo) variableInfo);
		} else if (variableInfo instanceof DoubleVariableInfo) {
			verificationTypeInfo.setDoubleVariableInfo((DoubleVariableInfo) variableInfo);
		} else if (variableInfo instanceof NullVariableInfo) {
			verificationTypeInfo.setNullVariableInfo((NullVariableInfo) variableInfo);
		} else if (variableInfo instanceof UninitializedThisVariableInfo) {
			verificationTypeInfo.setUninitializedThisVariableInfo((UninitializedThisVariableInfo) variableInfo);
		} else if (variableInfo instanceof ObjectVariableInfo) {
			verificationTypeInfo.setObjectVariableInfo((ObjectVariableInfo) variableInfo);
		} else if (variableInfo instanceof UninitializedVariableInfo) {
			verificationTypeInfo.setUninitializedVariableInfo((UninitializedVariableInfo) variableInfo);
		} else {
			// Should it return a VerificationTypeInfo without values set?
			verificationTypeInfo = null;
		}

		return verificationTypeInfo;
	}

}
