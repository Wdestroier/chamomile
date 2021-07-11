package com.github.wdestroier.chamomile.examples.dumper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import com.github.wdestroier.chamomile.classfile.ClassFile;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantClass;
import com.github.wdestroier.chamomile.classfile.constantkind.ConstantUtf8;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.SneakyThrows;

public class DumperMain {

	@SneakyThrows
	public static void main(String... args) {
		// Grab the dump from the jar, from a file or from anything that
		// can be converted to an InputStream
		var data = DumperMain.class.getResourceAsStream("/javaw.dmp");

		var dumper = new DumperMain();
		dumper.dumpClasses(data);
	}

	@SneakyThrows
	public void dumpClasses(InputStream data) {
		// The BackupInputStream will allow the class file to be unread
		// if it can't be fully parsed. You may run out of memory
		// If the file is too big you can use another strategy
		var backup = new BackupInputStream(new BufferedInputStream(
				data, 2048), true);

		var input = new MultiEndianInputStream(backup, 4, Endianness.BIG_ENDIAN);

		// Use the same factory instance to avoid creating a factory every time
		// a new class file needs to be read
		var pseudoStructureFactory = new PseudoStructureFactory();

		// Create a folder to store the dumped class files
		var dumpFolder = Files.createTempDirectory("dump_").toFile();

		System.out.println("Dump folder: " + dumpFolder.getAbsolutePath());

		while (hasNextMagic(input)) {
			var classFile = new ClassFile();

			// Try to read the entire class
			var readFully = readClass(classFile, input, pseudoStructureFactory);

			if (readFully) {
				// Save the class if it is read without exceptions
				writeClass(classFile, dumpFolder);
			} else {
				// Otherwise unread the read bytes and skip 1 byte
				unreadClass(input, backup);
				input.readSignedByte();
			}

			// Reset the backup buffer to allow it to be reused
			backup.getOutput().reset();

			System.gc();
		}
	}

	private boolean hasNextMagic(MultiEndianInputStream input) {
		var found = false;

		// Try to find the start of a class file. It is identified by 4 bytes:
		// 0xCA, 0xFE, 0xBA, 0xBE -> 0xCAFEBABE
		while (input.available() > 0) {
			if (input.readUnsignedByte() != 0xCA) {
				continue;
			}

			var magicByte2 = input.readUnsignedByte();
			if (magicByte2 != 0xFE) {
				input.unreadUnsignedByte(magicByte2);
				continue;
			}

			var magicByte3 = input.readUnsignedByte();
			if (magicByte3 != 0xBA) {
				input.unreadUnsignedByte(magicByte3);
				continue;
			}

			var magicByte4 = input.readUnsignedByte();
			if (magicByte4 != 0xBE) {
				input.unreadUnsignedByte(magicByte4);
				continue;
			}

			input.unreadUnsignedInt(0xCAFEBABE);

			found = true;
			break;
		}

		return found;
	}

	private boolean readClass(ClassFile classFile, MultiEndianInputStream input,
			PseudoStructureFactory pseudoStructureFactory) {
		boolean readFully;

		try {
			// Try to read the class file
			classFile.read(input, pseudoStructureFactory);
			readFully = true;
		} catch (Exception e) {
			readFully = false;
		}

		return readFully;
	}

	@SneakyThrows
	private void unreadClass(MultiEndianInputStream input, BackupInputStream backup) {
		// Unread all the bytes from the backup buffer
		input.unreadSignedBytes(backup.getOutput().toByteArray());
	}

	@SneakyThrows
	private void writeClass(ClassFile classFile, File folder) {
		// Find the internal class name. It is represented as com/example/project/Main
		var classNameIndex = ((ConstantClass) classFile.getConstantPool()[classFile.getThisClass()]).getNameIndex();
		var internalClassName = ((ConstantUtf8) classFile.getConstantPool()[classNameIndex]).getBytesString();

		var splitName = internalClassName.split("/");
		var packageCount = splitName.length - 1;

		// Create folders that represent the packages. Otherwise it will need
		// to be created later. Before packaging the classes in a jar file
		for (var i = 0; i < packageCount; i++) {
			folder = new File(folder, splitName[i]);
			folder.mkdir();
		}

		var file = new File(folder, splitName[packageCount] + ".class");
		file.createNewFile();

		// Write the class file bytes to the file
		classFile.write(file);
	}

}
