package com.github.wdestroier.chamomile.instruction.comparision.acmp;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IfAcmpeqInstruction implements AbstractAcmpInstruction<IfAcmpeqInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private int branchOffset;

	@Override
	public IfAcmpeqInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		branchOffset = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedShort(branchOffset);
	}

}
