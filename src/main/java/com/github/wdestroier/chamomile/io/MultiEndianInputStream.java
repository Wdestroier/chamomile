package com.github.wdestroier.chamomile.io;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.function.Supplier;

import com.github.wdestroier.chamomile.io.converter.PrimitiveTypesConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class MultiEndianInputStream extends InputStream {

	@Getter
	private Endianness endianness;

	private PushbackInputStream pushback;
	private PrimitiveTypesConverter converter;

	@Getter
	@Setter
	private long bytesRead;

	public MultiEndianInputStream(InputStream in, int bufferSize, Endianness endianness) {
		this.pushback = new PushbackInputStream(in, bufferSize);
		this.endianness = endianness;
		this.converter = new PrimitiveTypesConverter(endianness);
	}

	public MultiEndianInputStream(InputStream in, Endianness endianness) {
		this(in, 2, endianness);
	}

	@SneakyThrows
	public int read() {
		var read = pushback.read();
		bytesRead++;
		return read;
	}

	public short readUnsignedByte() {
		return converter.signedByteToUnsignedByte(read()); //0 - 255 (0xFF)
	}

	public byte readSignedByte() {
		return converter.unsignedByteToSignedByte(read());
	}

	public int readUnsignedShort() {
		return converter.unsignedShortFromBytes(read(), read()); //0 - 65535 (0xFFFF)
	}

	public short readSignedShort() {
		return converter.signedShortFromBytes(read(), read());
	}

	public long readUnsignedInt() {
		return converter.unsignedIntFromBytes(read(), read(), read(), read()); //0 - 0xFFFFFFFF
	}

	public int readSignedInt() {
		return converter.signedIntFromBytes(read(), read(), read(), read());
	}

	public BigInteger readUnsignedLong() {
		return converter.unsignedLongFromBytes(read(), read(), read(), read(),
				read(), read(), read(), read());
	}

	public long readSignedLong() {
		return converter.signedLongFromBytes(read(), read(), read(), read(),
				read(), read(), read(), read());
	}

	public short[] readUnsignedBytes(int length) {
		return createAndFillArray(short.class, length, () -> readUnsignedByte());
	}

	public byte[] readSignedBytes(int length) {
		return createAndFillArray(byte.class, length, () -> readSignedByte());
	}

	public int[] readUnsignedShorts(int length) {
		return createAndFillArray(int.class, length, () -> readUnsignedShort());
	}

	public short[] readSignedShorts(int length) {
		return createAndFillArray(short.class, length, () -> readSignedShort());
	}

	public long[] readUnsignedInts(int length) {
		return createAndFillArray(long.class, length, () -> readUnsignedInt());
	}

	public int[] readSignedInts(int length) {
		return createAndFillArray(int.class, length, () -> readSignedInt());
	}

	public BigInteger[] readUnsignedLongs(int length) {
		return createAndFillArray(BigInteger.class, length, () -> readUnsignedLong());
	}

	public long[] readSignedLongs(int length) {
		return createAndFillArray(long.class, length, () -> readSignedLong());
	}

	@SneakyThrows
	private void unread(int b) {
		pushback.unread(b);
		bytesRead--;
	}

	public void unreadUnsignedByte(short unsignedByte) {
		unread(unsignedByte);
	}

	public void unreadSignedByte(byte signedByte) {
		unread(signedByte);
	}

	public void unreadUnsignedShort(int unsignedShort) {
		for (var signedByte : reverseArray(converter.unsignedShortToBytes(unsignedShort))) unread(signedByte);
	}

	public void unreadSignedShort(short signedShort) {
		for (var signedByte : reverseArray(converter.signedShortToBytes(signedShort))) unread(signedByte);
	}

	public void unreadUnsignedInt(long unsignedInt) {
		for (var signedByte : reverseArray(converter.unsignedIntToBytes(unsignedInt))) unread(signedByte);
	}

	public void unreadSignedInt(int signedInt) {
		for (var signedByte : reverseArray(converter.signedIntToBytes(signedInt))) unread(signedByte);
	}

	public void unreadUnsignedLong(BigInteger unsignedLong) {
		for (var signedByte : reverseArray(converter.unsignedLongToBytes(unsignedLong))) unread(signedByte);
	}

	public void unreadSignedLong(long signedLong) {
		for (var signedByte : reverseArray(converter.signedLongToBytes(signedLong))) unread(signedByte);
	}

	public void unreadUnsignedBytes(short... unsignedBytes) {
		for (var unsignedByte : unsignedBytes) unreadUnsignedByte(unsignedByte);
	}

	public void unreadSignedBytes(byte... signedBytes) {
		for (var signedByte : signedBytes) unreadSignedByte(signedByte);
	}

	public void unreadUnsignedShorts(int... unsignedShorts) {
		for (var unsignedShort : unsignedShorts) unreadUnsignedShort(unsignedShort);
	}

	public void unreadSignedShorts(short... signedShorts) {
		for (var signedShort : signedShorts) unreadSignedShort(signedShort);
	}

	public void unreadUnsignedInts(long... unsignedInts) {
		for (var unsignedInt : unsignedInts) unreadUnsignedInt(unsignedInt);
	}

	public void unreadSignedInts(int... signedInts) {
		for (var signedInt : signedInts) unreadSignedInt(signedInt);
	}

	public void unreadUnsignedLongs(BigInteger... unsignedLongs) {
		for (var unsignedLong : unsignedLongs) unreadUnsignedLong(unsignedLong);
	}

	public void unreadSignedLongs(long... signedLongs) {
		for (var signedLong : signedLongs) unreadSignedLong(signedLong);
	}

	@SuppressWarnings("unchecked")
	private <T> T createAndFillArray(Class<?> type, int length, Supplier<?> supplier) {
		var array = Array.newInstance(type, length);

		for (var i = 0; i < length; i++) {
			Array.set(array, i, supplier.get());
		}

		return (T) array;
	}

	// 0x80000000 is [-128, 0, 0, 0] in little-endian
	// If it is written in the right order, it will become [0, 0, 0, -128] in the buffer
	// Because the first written is the last out
	// See PushbackInputStream#unread and PushbackInputStream#read
	// When the input stream reads the number again, it will read the wrong number
	// That's why it's necessary to reverse the order before pushing back
	private <T> T reverseArray(T array) {
		var arrayLength = Array.getLength(array);

		for (var i = 0; i < arrayLength / 2; i++) {
			var temp = Array.get(array, i);
			Array.set(array, i, Array.get(array, arrayLength - 1 - i));
			Array.set(array, arrayLength - 1 - i, temp);
		}

		return array;
	}

	@Override
	@SneakyThrows
	public int read(byte[] b, int off, int len) {
		return pushback.read(b, off, len);
	}

	@Override
	@SneakyThrows
    public int available() {
        return pushback.available();
    }

	@Override
	@SneakyThrows
	public long skip(long n) {
		return pushback.skip(n);
	}

	@Override
	@SneakyThrows
	public void close() {
		pushback.close();
	}

	public void setEndianness(Endianness endianness) {
		this.endianness = endianness;
		this.converter.setEndianness(endianness);
	}

}
