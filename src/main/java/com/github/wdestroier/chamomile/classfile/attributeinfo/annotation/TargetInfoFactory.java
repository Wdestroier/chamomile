package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation;

import com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.target.*;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
@AllArgsConstructor
public class TargetInfoFactory {

	private PseudoStructureFactory pseudoStructureFactory;

	//TODO Should this factory be removed and put in the TargetInfo#read() method?
	@SneakyThrows
	public TargetInfo getTargetInfo(MultiEndianInputStream data) {
		var targetInfo = new TargetInfo();
		var targetType = data.readUnsignedByte();

		switch (targetType) {
		case 0x00:
		case 0x01:
			var typeParameterTarget = new TypeParameterTarget().read(data, pseudoStructureFactory);
			targetInfo.setTypeParameterTarget(typeParameterTarget);
			break;
		case 0x10:
			var supertypeTarget = new SupertypeTarget().read(data, pseudoStructureFactory);
			targetInfo.setSupertypeTarget(supertypeTarget);
			break;
		case 0x11:
		case 0x12:
			var typeParameterBoundTarget = new TypeParameterBoundTarget().read(data, pseudoStructureFactory);
			targetInfo.setTypeParameterBoundTarget(typeParameterBoundTarget);
			break;
		case 0x13:
		case 0x14:
		case 0x15:
			var emptyTarget = new EmptyTarget().read(data, pseudoStructureFactory);
			targetInfo.setEmptyTarget(emptyTarget);
			break;
		case 0x16:
			var formalParameterTarget = new FormalParameterTarget().read(data, pseudoStructureFactory);
			targetInfo.setFormalParameterTarget(formalParameterTarget);
			break;
		case 0x17:
			var throwsTarget = new ThrowsTarget().read(data, pseudoStructureFactory);
			targetInfo.setThrowsTarget(throwsTarget);
			break;
		case 0x40:
		case 0x41:
			var localvarTarget = new LocalvarTarget().read(data, pseudoStructureFactory);
			targetInfo.setLocalvarTarget(localvarTarget);
			break;
		case 0x42:
			var catchTarget = new CatchTarget().read(data, pseudoStructureFactory);
			targetInfo.setCatchTarget(catchTarget);
			break;
		case 0x43:
		case 0x44:
		case 0x45:
		case 0x46:
			var offsetTarget = new OffsetTarget().read(data, pseudoStructureFactory);
			targetInfo.setOffsetTarget(offsetTarget);
			break;
		case 0x47:
		case 0x48:
		case 0x49:
		case 0x4A:
		case 0x4B:
			var typeArgumentTarget = new TypeArgumentTarget().read(data, pseudoStructureFactory);
			targetInfo.setTypeArgumentTarget(typeArgumentTarget);
			break;
		default:
			// Should it return a TargetInfo without fields set?
			targetInfo = null;
			break;
		}

		return targetInfo;
	}

}
