package com.github.wdestroier.chamomile.examples.benchmark;

import java.util.concurrent.TimeUnit;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.examples.class2json.ClassToJsonMain;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

public class BenchmarkMain {

	public static void main(String... args) {
		var startTime = System.nanoTime();

		var pseudoStructureFactory = new PseudoStructureFactory();

		for (var i = 0; i < 10_000; i++) {
			var sampleClass = ClassToJsonMain.class.getResourceAsStream("/HelloWorld.class.example");

			if (sampleClass == null) {
				System.out.println("Could not find class in the resources folder!");
				System.exit(-1);
			}

			var input = new MultiEndianInputStream(sampleClass, Endianness.BIG_ENDIAN);
			@SuppressWarnings("unused")
			ClassFile classFile = new ClassFile().read(input, pseudoStructureFactory);
		}

		var finishTime = System.nanoTime();

		System.out.printf("Took %sms!\n", TimeUnit.NANOSECONDS.toMillis(finishTime - startTime));
	}

}
