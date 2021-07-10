package com.github.wdestroier.chamomile.io;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

import com.github.wdestroier.chamomile.io.converter.PrimitiveTypesConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class MultiEndianOutputStream extends OutputStream {

	@Getter
	private Endianness endianness;

	private OutputStream output;
	private PrimitiveTypesConverter converter;

	@Getter
	@Setter
	private long bytesWritten;

	public MultiEndianOutputStream(OutputStream output, Endianness endianness) {
		this.output = output;
		this.endianness = endianness;
		this.converter = new PrimitiveTypesConverter(endianness);
	}

	@SneakyThrows
	public void write(int b) {
		output.write(b);
		bytesWritten++;
	}

	public void writeUnsignedByte(short unsignedByte) {
		write(unsignedByte);
	}

	public void writeSignedByte(byte signedByte) {
		write(signedByte);
	}

	public void writeUnsignedShort(int unsignedShort) {
		for (var signedByte : converter.unsignedShortToBytes(unsignedShort)) write(signedByte);
	}

	public void writeSignedShort(short signedShort) {
		for (var signedByte : converter.signedShortToBytes(signedShort)) write(signedByte);
	}

	public void writeUnsignedInt(long unsignedInt) {
		for (var signedByte : converter.unsignedIntToBytes(unsignedInt)) write(signedByte);
	}

	public void writeSignedInt(int signedInt) {
		for (var signedByte : converter.signedIntToBytes(signedInt)) write(signedByte);
	}

    public void writeUnsignedLong(BigInteger unsignedLong) {
		for (var signedByte : converter.unsignedLongToBytes(unsignedLong)) write(signedByte);
	}

	public void writeSignedLong(long signedLong) {
		for (var signedByte : converter.signedLongToBytes(signedLong)) write(signedByte);
	}

	public void writeUnsignedBytes(short... unsignedBytes) {
		for (var unsignedByte : unsignedBytes) writeUnsignedByte(unsignedByte);
	}

	public void writeSignedBytes(byte... signedBytes) {
		for (var signedByte : signedBytes) writeSignedByte(signedByte);
	}

	public void writeUnsignedShorts(int... unsignedShorts) {
		for (var unsignedShort : unsignedShorts) writeUnsignedShort(unsignedShort);
	}

	public void writeSignedShorts(short... signedShorts) {
		for (var signedShort : signedShorts) writeSignedShort(signedShort);
	}

	public void writeUnsignedInts(long... unsignedInts) {
		for (var unsignedInt : unsignedInts) writeUnsignedInt(unsignedInt);
	}

	public void writeSignedInts(int... signedInts) {
		for (var signedInt : signedInts) writeSignedInt(signedInt);
	}

	public void writeUnsignedLongs(BigInteger... unsignedLongs) {
		for (var unsignedLong : unsignedLongs) writeUnsignedLong(unsignedLong);
	}

	public void writeSignedLongs(long... signedLongs) {
		for (var signedLong : signedLongs) writeSignedLong(signedLong);
	}

	@Override
	@SneakyThrows
	public void flush() {
		output.flush();
	}

	@Override
	@SneakyThrows
	public void close() throws IOException {
		output.close();
	}

	public void setEndianness(Endianness endianness) {
		this.endianness = endianness;
		this.converter.setEndianness(endianness);
	}

}
