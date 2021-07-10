package com.github.wdestroier.chamomile.instruction.extended;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.instruction.math.IincInstruction;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WideInstruction implements AbstractExtendedInstruction<WideInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private short widenedOpcode;
	private int index;
	private int constant;

	@Override
	public WideInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		widenedOpcode = input.readUnsignedByte();
		index = input.readUnsignedShort();

		if (isWidenedOpcodeIinc()) {
			constant = input.readUnsignedShort();
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedByte(widenedOpcode);
		output.writeUnsignedShort(index);

		if (isWidenedOpcodeIinc()) {
			output.writeUnsignedShort(constant);
		}
	}

	private boolean isWidenedOpcodeIinc() {
		return IincInstruction.class.equals(InstructionFactory.instance.getInstructionClass(widenedOpcode));
	}

}
