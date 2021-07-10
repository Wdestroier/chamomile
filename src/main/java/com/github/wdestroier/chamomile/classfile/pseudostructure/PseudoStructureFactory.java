package com.github.wdestroier.chamomile.classfile.pseudostructure;

import com.github.wdestroier.chamomile.classfile.attributeinfo.AttributeInfoFactory;
import com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.TargetInfoFactory;
import com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.ValueFactory;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.StackMapFrameFactory;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.verificationtype.VariableInfoFactory;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.verificationtype.VerificationTypeInfoFactory;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantKindFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

@Getter
@Setter
public class PseudoStructureFactory {

	@Delegate
	private ConstantKindFactory constantKindFactory;
	@Delegate
	private AttributeInfoFactory attributeInfoFactory;
	@Delegate
	private StackMapFrameFactory stackMapFrameFactory;
	@Delegate
	private VariableInfoFactory variableInfoFactory;
	@Delegate
	private VerificationTypeInfoFactory verificationTypeInfoFactory;
	@Delegate
	private ValueFactory valueFactory;
	@Delegate
	private TargetInfoFactory targetInfoFactory;

	public PseudoStructureFactory() {
		constantKindFactory = new ConstantKindFactory(this);
		attributeInfoFactory = null;
		stackMapFrameFactory = new StackMapFrameFactory(this);
		variableInfoFactory = new VariableInfoFactory(this);
		verificationTypeInfoFactory = new VerificationTypeInfoFactory(variableInfoFactory);
		valueFactory = new ValueFactory(this);
		targetInfoFactory = new TargetInfoFactory(this);
	}

	public PseudoStructureFactory(AttributeInfoFactory attributeInfoFactory) {
		this();
		this.attributeInfoFactory = attributeInfoFactory;
	}

}
