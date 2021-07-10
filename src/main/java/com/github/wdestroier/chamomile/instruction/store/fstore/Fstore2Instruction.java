package com.github.wdestroier.chamomile.instruction.store.fstore;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fstore2Instruction implements AbstractFstoreInstruction<Fstore2Instruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());

	@Override
	public Fstore2Instruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
	}

	@Override
	public short getIndex() {
		return 2;
	}

}
