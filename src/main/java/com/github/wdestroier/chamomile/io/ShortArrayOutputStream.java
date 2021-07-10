package com.github.wdestroier.chamomile.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

public class ShortArrayOutputStream extends OutputStream {

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	protected short buf[];
	protected int count;

	public ShortArrayOutputStream() {
		this(32);
	}

	public ShortArrayOutputStream(int size) {
		if (size < 0) {
			throw new IllegalArgumentException("Negative initial size: " + size);
		}

		buf = new short[size];
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity - buf.length > 0) {
			grow(minCapacity);
		}
	}

	private void grow(int minCapacity) {
		int oldCapacity = buf.length;
		int newCapacity = oldCapacity << 1;

		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}

		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}

		buf = Arrays.copyOf(buf, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError();
		}

		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	@Override
	public synchronized void write(int b) {
		ensureCapacity(count + 1);
		buf[count] = (short) b;
		count += 1;
	}

	@Override
	public synchronized void write(byte b[], int off, int len) {
		Objects.checkFromIndexSize(off, len, b.length);
		ensureCapacity(count + len);
		arraycopy(b, off, buf, count, len);
		count += len;
	}

	public synchronized void write(short s[], int off, int len) {
		Objects.checkFromIndexSize(off, len, s.length);
		ensureCapacity(count + len);
		System.arraycopy(s, off, buf, count, len);
		count += len;
	}

	public void writeUnsignedBytes(short b[]) {
		write(b, 0, b.length);
	}

	public synchronized void writeTo(OutputStream out) throws IOException {
        Objects.checkFromIndexSize(0, count, buf.length);

        for (int i = 0 ; i < count ; i++) {
            write(buf[0 + i]);
        }
	}

	public synchronized void reset() {
		count = 0;
	}

	public synchronized short[] toUnsignedByteArray() {
		return Arrays.copyOf(buf, count);
	}

	public synchronized int size() {
		return count;
	}

	@Override
	public synchronized String toString() {
		return Arrays.toString(buf);
	}

	@Override
	public void close() throws IOException {}

	protected void arraycopy(byte[] src, int srcPos, short[] dest, int destPos, int length) {
		for (int i = 0; i < length; i++) {
			// short[] is an array of unsigned bytes
			dest[destPos + i] = (short) (src[srcPos + i] & 0xFF);
		}
	}

}
