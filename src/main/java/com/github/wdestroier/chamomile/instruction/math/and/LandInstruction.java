package com.github.wdestroier.chamomile.instruction.math.and;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LandInstruction implements AbstractAndInstruction<LandInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());

	@Override
	public LandInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
	}

}
