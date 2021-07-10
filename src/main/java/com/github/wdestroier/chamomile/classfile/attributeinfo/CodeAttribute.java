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
public class CodeAttribute implements PseudoStructure<CodeAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int maxStack;
	private int maxLocals;
	private long codeLength;
	private short[] code;
	private int exceptionTableLength;
	private ExceptionHandler[] exceptionTable;
	private int attributesCount;
	private Object[] attributes;

	@Override
	public CodeAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		maxStack = input.readUnsignedShort();
		maxLocals = input.readUnsignedShort();
		codeLength = input.readUnsignedInt();
		code = input.readUnsignedBytes((int) codeLength);
		exceptionTableLength = input.readUnsignedShort();
		exceptionTable = new ExceptionHandler[exceptionTableLength];

		for (var i = 0; i < exceptionTableLength; i++) {
			exceptionTable[i] = new ExceptionHandler().read(input, pseudoStructureFactory);
		}

		attributesCount = input.readUnsignedShort();
		attributes = new Object[attributesCount];

		for (var i = 0; i < attributesCount; i++) {
			attributes[i] = pseudoStructureFactory.getAttributeInfo(input);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(maxStack);
		output.writeUnsignedShort(maxLocals);
		output.writeUnsignedInt(codeLength);
		output.writeUnsignedBytes(code);

		output.writeUnsignedShort(exceptionTableLength);

		for (var exceptionHandler : exceptionTable) {
			exceptionHandler.write(output);
		}

		output.writeUnsignedShort(attributesCount);

		for (var attribute : attributes) {
			((PseudoStructure<?>) attribute).write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ExceptionHandler implements PseudoStructure<ExceptionHandler> {

		/**
		 * Must be a valid index into the code array of the opcode of an instruction.
		 * The value of startPc must be less than the value of endPc.
		 */
		private int startPc;

		/**
		 * Must also be a valid index into the code array of the opcode of an instruction
		 * or must be equal to the code array length.
		 */
		private int endPc;

		/**
		 * Must be a valid index into the code array and must be the index of the opcode
		 * of an instruction. Indicates the start of the exception handler.
		 */
		private int handlerPc;
		private int catchType;

		@Override
		public ExceptionHandler read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			startPc = input.readUnsignedShort();
			endPc = input.readUnsignedShort();
			handlerPc = input.readUnsignedShort();
			catchType = input.readUnsignedShort();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedShort(startPc);
			output.writeUnsignedShort(endPc);
			output.writeUnsignedShort(handlerPc);
			output.writeUnsignedShort(catchType);
		}

	}

}
