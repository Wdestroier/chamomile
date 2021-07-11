package com.github.wdestroier.chamomile.examples.class2json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.SneakyThrows;

public class ClassToJsonMain {

	@SneakyThrows
	public static void main(String... args) {
		var sampleClass = ClassToJsonMain.class.getResourceAsStream("/HelloWorld.class.example");

		if (sampleClass == null) {
			System.out.println("Could not find class in the resources folder!");
			System.exit(-1);
		}

		var input = new MultiEndianInputStream(sampleClass, Endianness.BIG_ENDIAN);

		var classFile = new ClassFile().read(input);

		var writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
		var json = writer.writeValueAsString(classFile);

		System.out.println(json);

		// Good luck converting back to a class file
	}

}
