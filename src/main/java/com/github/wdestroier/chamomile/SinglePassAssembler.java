package com.github.wdestroier.chamomile;

import java.util.List;

import com.github.wdestroier.chamomile.instruction.Instruction;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;
import com.github.wdestroier.chamomile.io.ShortArrayOutputStream;

public class SinglePassAssembler {

	public void assemble(List<Instruction<?>> instructions, MultiEndianOutputStream output) {
		instructions.forEach(instruction -> instruction.write(output));
	}

	public short[] assemble(List<Instruction<?>> instructions) {
		var output = new ShortArrayOutputStream();

		assemble(instructions, new MultiEndianOutputStream(output, Endianness.BIG_ENDIAN));

		return output.toUnsignedByteArray();
	}

}
