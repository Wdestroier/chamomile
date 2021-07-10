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
public class MultianewarrayInstruction implements AbstractExtendedInstruction<MultianewarrayInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private int index;
	private short dimensions;

	@Override
	public MultianewarrayInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		index = input.readUnsignedShort();
		dimensions = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedShort(index);
		output.writeUnsignedByte(dimensions);
	}

}
