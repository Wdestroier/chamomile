package com.github.wdestroier.chamomile.instruction.math;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IincInstruction implements AbstractMathInstruction<IincInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private short index;
	private byte constant;

	@Override
	@SneakyThrows
	public IincInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		index = input.readUnsignedByte();
		constant = input.readSignedByte();

		return this;
	}

	@Override
	@SneakyThrows
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedByte(index);
		output.writeSignedByte(constant);
	}

}
