package com.github.wdestroier.chamomile.instruction.math.shift.arithmetic;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Int shift right Instruction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IshrInstruction implements AbstractArithmeticShiftInstruction<IshrInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());

	@Override
	public IshrInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
	}

}
