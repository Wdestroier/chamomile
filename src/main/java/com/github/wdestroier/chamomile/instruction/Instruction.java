package com.github.wdestroier.chamomile.instruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;
import com.github.wdestroier.chamomile.io.ShortArrayInputStream;
import com.github.wdestroier.chamomile.io.ShortArrayOutputStream;

import lombok.SneakyThrows;

public interface Instruction<T extends Instruction<T>> {

	short getOpcode();

	void setOpcode(short opcode);

	T read(MultiEndianInputStream input);

	@SneakyThrows
	default T read(File file) {
		return read(new MultiEndianInputStream(new FileInputStream(file), 2, Endianness.BIG_ENDIAN));
	}

	default T read(short... input) {
		return read(new MultiEndianInputStream(new ShortArrayInputStream(input), 2, Endianness.BIG_ENDIAN));
	}

	void write(MultiEndianOutputStream output);

	@SneakyThrows
	default void write(File file) {
		write(new MultiEndianOutputStream(new FileOutputStream(file), Endianness.BIG_ENDIAN));
	}

	@SneakyThrows
	default short[] write() {
		var output = new ShortArrayOutputStream();
		write(new MultiEndianOutputStream(output, Endianness.BIG_ENDIAN));
		return output.toUnsignedByteArray();
	}

}
