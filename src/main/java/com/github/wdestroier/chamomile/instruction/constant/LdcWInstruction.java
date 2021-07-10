package com.github.wdestroier.chamomile.instruction.constant;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LdcWInstruction implements AbstractConstantInstruction<LdcWInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private int index;

	@Override
	public LdcWInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		index = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedShort(index);
	}

}
