package com.github.wdestroier.chamomile.classfile.pseudostructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.SneakyThrows;

public interface PseudoStructure<T extends PseudoStructure<T>> {

	T read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory);

	default T read(MultiEndianInputStream input) {
		return read(input, new PseudoStructureFactory());
	}

	@SneakyThrows
	default T read(File file) {
		return read(new MultiEndianInputStream(new FileInputStream(file), Endianness.BIG_ENDIAN));
	}

	default T read(byte... input) {
		return read(new MultiEndianInputStream(new ByteArrayInputStream(input), Endianness.BIG_ENDIAN));
	}

	void write(MultiEndianOutputStream output);

	@SneakyThrows
	default void write(File file) {
		write(new MultiEndianOutputStream(new FileOutputStream(file), Endianness.BIG_ENDIAN));
	}

	@SneakyThrows
	default byte[] write() {
		var output = new ByteArrayOutputStream();
		write(new MultiEndianOutputStream(output, Endianness.BIG_ENDIAN));
		return output.toByteArray();
	}

}
