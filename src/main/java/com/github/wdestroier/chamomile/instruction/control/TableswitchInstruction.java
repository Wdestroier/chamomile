package com.github.wdestroier.chamomile.instruction.control;

import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableswitchInstruction implements AbstractControlInstruction<TableswitchInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private byte[] padding;
	private int defaultOffset;
	private int low, high;
	private int[] jumpOffsets;

	@Override
	public TableswitchInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		padding = input.readSignedBytes((int) (input.getBytesRead() % 4));
		defaultOffset = input.readSignedInt();
		low = input.readSignedInt();
		high = input.readSignedInt();
		jumpOffsets = input.readSignedInts((int) (high - low + 1));

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeSignedBytes(padding);
		output.writeSignedInt(defaultOffset);
		output.writeSignedInt(low);
		output.writeSignedInt(high);
		output.writeSignedInts(jumpOffsets);
	}

}
