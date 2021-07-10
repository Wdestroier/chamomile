package com.github.wdestroier.chamomile.instruction.store.dstore;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DstoreInstruction implements AbstractDstoreInstruction<DstoreInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private short index;

	@Override
	public DstoreInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		index = input.readUnsignedByte();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeUnsignedByte(index);
	}

}
