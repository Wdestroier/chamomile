package com.github.wdestroier.chamomile.instruction.reference;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvokevirtualInstruction implements AbstractReferenceInstruction<InvokevirtualInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private int index;

	@Override
	public InvokevirtualInstruction read(MultiEndianInputStream input) {
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
