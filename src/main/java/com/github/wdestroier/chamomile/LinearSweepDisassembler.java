package com.github.wdestroier.chamomile;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.wdestroier.chamomile.instruction.Instruction;
import com.github.wdestroier.chamomile.instruction.InstructionFactory;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.ShortArrayInputStream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LinearSweepDisassembler {

	private InstructionFactory instructionFactory;

	public LinearSweepDisassembler() {
		this(new InstructionFactory());
	}

	public List<Instruction<?>> disassemble(MultiEndianInputStream input) {
		var bytesRead = input.getBytesRead();

		// Needs to be aligned to read the Tableswitch and Tablelookup instructions
		input.setBytesRead(0);

		var instructions = new ArrayList<Instruction<?>>();

		while (input.available() > 0) {
			instructions.add(instructionFactory.getInstruction(input));
		}

		input.setBytesRead(input.getBytesRead() + bytesRead);

		return instructions;
	}

	public List<Instruction<?>> disassemble(short... code) {
		return disassemble(new MultiEndianInputStream(new BufferedInputStream(
				new ShortArrayInputStream(code)), Endianness.BIG_ENDIAN));
	}

}
