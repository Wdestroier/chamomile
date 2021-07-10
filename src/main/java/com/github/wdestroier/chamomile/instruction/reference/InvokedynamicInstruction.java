package com.github.wdestroier.chamomile.instruction.reference;

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
public class InvokedynamicInstruction implements AbstractReferenceInstruction<InvokedynamicInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private int index;
	private byte thirdOperand;
	private byte fourthOperand;

	@Override
	@SneakyThrows
	public InvokedynamicInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		index = input.readUnsignedShort();
		thirdOperand = input.readSignedByte();
		fourthOperand = input.readSignedByte();

		return this;
	}

	@Override
	@SneakyThrows
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedShort(index);
		output.writeSignedByte(thirdOperand);
		output.writeSignedByte(fourthOperand);
	}

}
