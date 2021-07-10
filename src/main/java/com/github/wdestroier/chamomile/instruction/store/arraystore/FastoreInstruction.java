package com.github.wdestroier.chamomile.instruction.store.arraystore;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastoreInstruction implements AbstractArrayStoreInstruction<FastoreInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());

	@Override
	public FastoreInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
	}

}
