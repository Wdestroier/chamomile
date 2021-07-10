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
public class LocalVariableTableAttribute implements PseudoStructure<LocalVariableTableAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int localVariableTableLength;
	private LocalVariable[] localVariableTable;

	@Override
	public LocalVariableTableAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		localVariableTableLength = input.readUnsignedShort();
		localVariableTable = new LocalVariable[localVariableTableLength];

		for (var i = 0; i < localVariableTableLength; i++) {
			localVariableTable[i] = new LocalVariable().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(localVariableTableLength);

		for (var localVariable : localVariableTable) {
			localVariable.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LocalVariable implements PseudoStructure<LocalVariable> {

		private int startPc;
		private int length;
		private int nameIndex;
		private int descriptorIndex;
		private int index;

		@Override
		public LocalVariable read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			startPc = input.readUnsignedShort();
			length = input.readUnsignedShort();
			nameIndex = input.readUnsignedShort();
			descriptorIndex = input.readUnsignedShort();
			index = input.readUnsignedShort();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedShort(startPc);
			output.writeUnsignedShort(length);
			output.writeUnsignedShort(nameIndex);
			output.writeUnsignedShort(descriptorIndex);
			output.writeUnsignedShort(index);
		}

	}

}
