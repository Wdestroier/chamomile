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
public class SipushInstruction implements AbstractConstantInstruction<SipushInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private short shortValue;

	@Override
	@SneakyThrows
	public SipushInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		shortValue = input.readSignedShort();

		return this;
	}

	@Override
	@SneakyThrows
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeSignedShort(shortValue);
	}

}
