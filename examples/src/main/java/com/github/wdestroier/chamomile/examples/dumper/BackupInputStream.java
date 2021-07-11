package com.github.wdestroier.chamomile.examples.dumper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BackupInputStream extends InputStream {

	@Delegate(excludes = InputStreamExclusions.class)
	private BufferedInputStream input;

	@Getter
	@Setter
	private ByteArrayOutputStream output;

	@Getter
	@Setter
	private boolean backupEnabled;

	public BackupInputStream(BufferedInputStream input, boolean backupEnabled) {
		this(input, new ByteArrayOutputStream(), backupEnabled);
	}

	@Override
	@SneakyThrows
	public int read() {
		var read = input.read();

		if (backupEnabled) {
			output.write(read);
		}

		return read;
	}

	@Override
	@SneakyThrows
	public int read(byte[] b, int off, int len) {
		var read = input.read(b, off, len);

		if (backupEnabled) {
			output.write(read);
		}

		return read;
	}

    private interface InputStreamExclusions {
    	int read();
    	int read(byte b[], int off, int len);
    }

}
