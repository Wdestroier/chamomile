package com.github.wdestroier.chamomile.instruction.extended;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GotoWInstruction implements AbstractExtendedInstruction<GotoWInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private long branchOffset;

	@Override
	public GotoWInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		branchOffset = input.readUnsignedInt();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedInt(branchOffset);
	}

}
