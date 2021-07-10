package com.github.wdestroier.chamomile.instruction.constant.iconst;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Iconst2Instruction implements AbstractIconstInstruction<Iconst2Instruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());

	@Override
	public Iconst2Instruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
	}

	@Override
	public int getConstant() {
		return 2;
	}

}
