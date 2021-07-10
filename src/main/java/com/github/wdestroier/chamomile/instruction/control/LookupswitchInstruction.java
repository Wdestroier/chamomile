package com.github.wdestroier.chamomile.instruction.control;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupswitchInstruction implements AbstractControlInstruction<LookupswitchInstruction> {

	private short opcode = InstructionFactory.instance.getOpcode(this.getClass());
	private byte[] padding;
	private int defaultOffset;
	private int npairs;
	private MatchOffsetPair[] pairs;

	@Override
	public LookupswitchInstruction read(MultiEndianInputStream input) {
		opcode = input.readUnsignedByte();
		padding = input.readSignedBytes((int) (input.getBytesRead() % 4));
		defaultOffset = input.readSignedInt();
		npairs = input.readSignedInt();
		pairs = new MatchOffsetPair[npairs];

		for (int i = 0; i < npairs; i++) {
			pairs[i] = new MatchOffsetPair().read(input, null);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(opcode);
		output.writeSignedBytes(padding);
		output.writeSignedInt(defaultOffset);
		output.writeSignedInt(npairs);

		for (MatchOffsetPair pair : pairs) {
			pair.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MatchOffsetPair implements PseudoStructure<MatchOffsetPair> {

		private int match, offset;

		@Override
		public MatchOffsetPair read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			match = input.readSignedInt();
			offset = input.readSignedInt();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeSignedInt(match);
			output.writeSignedInt(offset);
		}

	}

}
