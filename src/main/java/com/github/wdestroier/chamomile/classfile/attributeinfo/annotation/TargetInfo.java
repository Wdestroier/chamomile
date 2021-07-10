package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation;

import com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.target.*;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.Data;

/**
 * This class is an union type. Only 1 field is used at the time.
 */
@Data
public class TargetInfo implements PseudoStructure<TargetInfo> {

	private TypeParameterTarget typeParameterTarget;
	private SupertypeTarget supertypeTarget;
	private TypeParameterBoundTarget typeParameterBoundTarget;
	private EmptyTarget emptyTarget;
	private FormalParameterTarget formalParameterTarget;
	private ThrowsTarget throwsTarget;
	private LocalvarTarget localvarTarget;
	private CatchTarget catchTarget;
	private OffsetTarget offsetTarget;
	private TypeArgumentTarget typeArgumentTarget;

	@Override
	public TargetInfo read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		return pseudoStructureFactory.getTargetInfo(input);
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		if (typeParameterTarget != null) {
			typeParameterTarget.write(output);
		} else if (supertypeTarget != null) {
			supertypeTarget.write(output);
		} else if (typeParameterBoundTarget != null) {
			typeParameterBoundTarget.write(output);
		} else if (emptyTarget != null) {
			emptyTarget.write(output);
		} else if (formalParameterTarget != null) {
			formalParameterTarget.write(output);
		} else if (throwsTarget != null) {
			throwsTarget.write(output);
		} else if (localvarTarget != null) {
			localvarTarget.write(output);
		} else if (catchTarget != null) {
			catchTarget.write(output);
		} else if (offsetTarget != null) {
			offsetTarget.write(output);
		} else if (typeArgumentTarget != null) {
			typeArgumentTarget.write(output);
		}
	}

	public TargetInfo setTypeParameterTarget(TypeParameterTarget typeParameterTarget) {
		this.typeParameterTarget = typeParameterTarget;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		formalParameterTarget = null;
		throwsTarget = null;
		localvarTarget = null;
		catchTarget = null;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setSupertypeTarget(SupertypeTarget supertypeTarget) {
		typeParameterTarget = null;
		this.supertypeTarget = supertypeTarget;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		formalParameterTarget = null;
		throwsTarget = null;
		localvarTarget = null;
		catchTarget = null;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setTypeParameterBoundTarget(TypeParameterBoundTarget typeParameterBoundTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		this.typeParameterBoundTarget = typeParameterBoundTarget;
		emptyTarget = null;
		formalParameterTarget = null;
		throwsTarget = null;
		localvarTarget = null;
		catchTarget = null;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setEmptyTarget(EmptyTarget emptyTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		this.emptyTarget = emptyTarget;
		formalParameterTarget = null;
		throwsTarget = null;
		localvarTarget = null;
		catchTarget = null;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setFormalParameterTarget(FormalParameterTarget formalParameterTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		this.formalParameterTarget = formalParameterTarget;
		throwsTarget = null;
		localvarTarget = null;
		catchTarget = null;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setThrowsTarget(ThrowsTarget throwsTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		formalParameterTarget = null;
		this.throwsTarget = throwsTarget;
		localvarTarget = null;
		catchTarget = null;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setLocalvarTarget(LocalvarTarget localvarTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		formalParameterTarget = null;
		throwsTarget = null;
		this.localvarTarget = localvarTarget;
		catchTarget = null;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setCatchTarget(CatchTarget catchTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		formalParameterTarget = null;
		throwsTarget = null;
		localvarTarget = null;
		this.catchTarget = catchTarget;
		offsetTarget = null;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setOffsetTarget(OffsetTarget offsetTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		formalParameterTarget = null;
		throwsTarget = null;
		localvarTarget = null;
		catchTarget = null;
		this.offsetTarget = offsetTarget;
		typeArgumentTarget = null;

		return this;
	}

	public TargetInfo setTypeArgumentTarget(TypeArgumentTarget typeArgumentTarget) {
		typeParameterTarget = null;
		supertypeTarget = null;
		typeParameterBoundTarget = null;
		emptyTarget = null;
		formalParameterTarget = null;
		throwsTarget = null;
		localvarTarget = null;
		catchTarget = null;
		offsetTarget = null;
		this.typeArgumentTarget = typeArgumentTarget;

		return this;
	}

}
