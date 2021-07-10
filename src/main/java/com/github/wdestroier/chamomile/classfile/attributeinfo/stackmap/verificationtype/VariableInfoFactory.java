package com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.verificationtype;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
@AllArgsConstructor
public class VariableInfoFactory {

	private PseudoStructureFactory pseudoStructureFactory;

	@SneakyThrows
	public Object getVariableInfo(MultiEndianInputStream data) {
		Object variableInfo;
		var tag = data.readUnsignedByte();
		data.unreadUnsignedByte(tag);

		switch (tag) {
		case VerificationType.ITEM_TOP:
			variableInfo = new TopVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_INTEGER:
			variableInfo = new IntegerVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_FLOAT:
			variableInfo = new FloatVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_NULL:
			variableInfo = new NullVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_UNINITIALIZEDTHIS:
			variableInfo = new UninitializedThisVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_OBJECT:
			variableInfo = new ObjectVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_UNINITIALIZED:
			variableInfo = new UninitializedVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_LONG:
			variableInfo = new LongVariableInfo().read(data, pseudoStructureFactory);
			break;
		case VerificationType.ITEM_DOUBLE:
			variableInfo = new DoubleVariableInfo().read(data, pseudoStructureFactory);
			break;
		default:
			variableInfo = null;
			break;
		}

		return variableInfo;
	}

}
