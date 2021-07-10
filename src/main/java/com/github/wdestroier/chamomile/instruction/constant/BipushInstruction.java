package com.github.wdestroier.chamomile.instruction.constant;

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
public class BipushInstruction implements AbstractConstantInstruction<BipushInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private byte byteValue;

	@Override
	@SneakyThrows
	public BipushInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		byteValue = input.readSignedByte();

		return this;
	}

	@Override
	@SneakyThrows
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeSignedByte(byteValue);
	}

}
