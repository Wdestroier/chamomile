package com.github.wdestroier.chamomile.instruction.constant.dconst;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dconst1Instruction implements AbstractDconstInstruction<Dconst1Instruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());

	@Override
	public Dconst1Instruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
	}

	@Override
	public double getConstant() {
		return 1.0d;
	}

}
