package com.github.wdestroier.chamomile.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

import lombok.experimental.Delegate;

public class ShortArrayInputStream extends InputStream {

	protected SyntheticByteArrayInputStream input;
	@Delegate(excludes = InputStreamExclusions.class)
	protected ByteArrayInputStream delegated;

	protected short[] buf;

	public ShortArrayInputStream(short[] buf) {
		this.input = new SyntheticByteArrayInputStream(new byte[0]);
		this.delegated = input;
		this.buf = buf;
		this.input.setCount(buf.length);
	}

	@Override
	public synchronized int read() {
		int read;

		if (input.getPos() < input.getCount()) {
			var pos = input.getPos();
			read = buf[pos++] & 0xff;
			input.setPos(pos);
		} else {
			read = -1;
		}

		return read;
	}

	public synchronized int read(short s[], int off, int len) {
		Objects.checkFromIndexSize(off, len, s.length);

		var pos = input.getPos();
		var count = input.getCount();

		if (pos >= count) {
			return -1;
		}

		var avail = count - pos;
		if (len > avail) {
			len = avail;
		}
		if (len <= 0) {
			return 0;
		}
		System.arraycopy(buf, pos, s, off, len);
		input.setPos(pos + len);

		return len;
	}

	@Override
	public synchronized int read(byte b[], int off, int len) {
		Objects.checkFromIndexSize(off, len, b.length);

		var pos = input.getPos();
		var count = input.getCount();

		if (pos >= count) {
			return -1;
		}

		var avail = count - pos;
		if (len > avail) {
			len = avail;
		}
		if (len <= 0) {
			return 0;
		}
		arraycopy(buf, pos, b, off, len);
		input.setPos(pos + len);

		return len;
	}

	public synchronized short[] readAllUnsignedBytes() {
		var count = input.getCount();
		var result = Arrays.copyOfRange(buf, input.getPos(), count);
		input.setPos(count);

		return result;
	}

	@Override
	public synchronized byte[] readAllBytes() {
		var count = input.getCount();
		var result = copyOfRange(buf, input.getPos(), count);
		input.setPos(count);

		return result;
	}

	@Override
	public synchronized long transferTo(OutputStream out) throws IOException {
		var pos = input.getPos();
		var count = input.getCount();
		var len = count - pos;

		Objects.checkFromIndexSize(pos, len, buf.length);
		for (var i = 0; i < len; i++) {
			out.write(buf[pos + i]);
		}

		input.setPos(count);
		return len;
	}

	protected byte[] copyOfRange(short[] original, int from, int to) {
		var newLength = to - from;
		if (newLength < 0)
			throw new IllegalArgumentException(from + " > " + to);
		var copy = new byte[newLength];
		System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));

		return copy;
	}

	protected void arraycopy(short[] src, int srcPos, byte[] dest, int destPos, int length) {
		for (var i = 0; i < length; i++) {
			// short[] is an array of unsigned bytes
			dest[destPos + i] = (byte) src[srcPos + i];
		}
	}

	protected class SyntheticByteArrayInputStream extends ByteArrayInputStream {

		public SyntheticByteArrayInputStream(byte[] buf) {
			super(buf);
		}

		public byte[] getBuf() {
			return buf;
		}

		public void setBuf(byte[] buf) {
			this.buf = buf;
		}

		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

		public int getMark() {
			return mark;
		}

		public void setMark(int mark) {
			this.mark = mark;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}

	private interface InputStreamExclusions {
		int read();
		int read(byte b[]);
		int read(byte b[], int off, int len);
		byte[] readAllBytes();
		byte[] readNBytes(int len);
		int readNBytes(byte[] b, int off, int len);
		long transferTo(OutputStream out);
	}

}
