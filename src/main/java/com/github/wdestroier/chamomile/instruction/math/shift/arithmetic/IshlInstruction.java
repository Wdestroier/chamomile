package com.github.wdestroier.chamomile.instruction.math.shift.arithmetic;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Int shift left Instruction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IshlInstruction implements AbstractArithmeticShiftInstruction<IshlInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());

	@Override
	public IshlInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
	}

}
