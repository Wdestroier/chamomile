package com.github.wdestroier.chamomile.io.converter;

import com.github.wdestroier.chamomile.io.Endianness;

import lombok.NonNull;

public class ByteTypeConverter extends AbstractTypeConverter {

	public ByteTypeConverter(@NonNull Endianness endianness) {
		super(endianness);
	}

	public short signedByteToUnsignedByte(int signed) {
		return (short) (signed & 0xFF);
	}

	public byte unsignedByteToSignedByte(int unsigned) {
		return (byte) unsigned;
	}

	public byte[] unsignedBytesToSignedBytes(short... unsignedBytes) {
		// It's only going to convert the type, doesn't need to convert the endianness.

		var length = unsignedBytes.length;

		var signedBytes = new byte[length];

		for (var i = 0; i < length; i++) {
			signedBytes[i] = (byte) unsignedBytes[i];
		}

		return signedBytes;
	}

	public short[] signedBytesToUnsignedBytes(byte... signedBytes) {
		var length = signedBytes.length;

		var unsignedBytes = new short[length];

		for (var i = 0; i < length; i++) {
			unsignedBytes[i] = (short) (signedBytes[i] & 0xFF);
		}

		return unsignedBytes;
	}

}
