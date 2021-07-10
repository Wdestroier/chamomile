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
public class InvokeinterfaceInstruction implements AbstractReferenceInstruction<InvokeinterfaceInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private int index;
	private short count;
	private byte fourthOperand; //Must always be zero.

	@Override
	@SneakyThrows
	public InvokeinterfaceInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		index = input.readUnsignedShort();
		count = input.readUnsignedByte();
		fourthOperand = input.readSignedByte();

		return this;
	}

	@Override
	@SneakyThrows
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedShort(index);
		output.writeUnsignedByte(count);
		output.writeSignedByte(fourthOperand);
	}

}
