package com.github.wdestroier.chamomile.classfile.attributeinfo;

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
public class LocalVariableTypeTableAttribute implements PseudoStructure<LocalVariableTypeTableAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int localVariableTypeTableLength;
	private LocalVariableType[] localVariableTypeTable;

	@Override
	public LocalVariableTypeTableAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		localVariableTypeTableLength = input.readUnsignedShort();
		localVariableTypeTable = new LocalVariableType[localVariableTypeTableLength];

		for (var i = 0; i < attributeLength; i++) {
			localVariableTypeTable[i] = new LocalVariableType().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(localVariableTypeTableLength);

		for (var localVariableType : localVariableTypeTable) {
			localVariableType.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LocalVariableType implements PseudoStructure<LocalVariableType> {

		private int startPc;
		private int length;
		private int nameIndex;
		private int signatureIndex;
		private int index;

		@Override
		public LocalVariableType read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			startPc = input.readUnsignedShort();
			length = input.readUnsignedShort();
			nameIndex = input.readUnsignedShort();
			signatureIndex = input.readUnsignedShort();
			index = input.readUnsignedShort();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedShort(startPc);
			output.writeUnsignedShort(length);
			output.writeUnsignedShort(nameIndex);
			output.writeUnsignedShort(signatureIndex);
			output.writeUnsignedShort(index);
		}

	}

}
