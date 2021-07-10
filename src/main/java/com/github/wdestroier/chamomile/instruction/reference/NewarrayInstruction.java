package com.github.wdestroier.chamomile.instruction.reference;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewarrayInstruction implements AbstractReferenceInstruction<NewarrayInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private short atype;

	@Override
	public NewarrayInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		atype = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedByte(atype);
	}

	public static class ArrayType {

		public static final short T_BOOLEAN = 4;
		public static final short T_CHAR = 5;
		public static final short T_FLOAT = 6;
		public static final short T_DOUBLE = 7;
		public static final short T_BYTE = 8;
		public static final short T_SHORT = 9;
		public static final short T_INT = 10;
		public static final short T_LONG = 11;

	}

}
