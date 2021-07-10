package com.github.wdestroier.chamomile.io.converter;

import com.github.wdestroier.chamomile.io.Endianness;

public class ShortTypeConverter extends AbstractTypeConverter {

	public ShortTypeConverter(Endianness endianness) {
		super(endianness);
	}

	public byte[] signedShortToBytes(short s2) {
		return unsignedShortToBytes(s2);
	}

	public byte[] unsignedShortToBytes(int u2) {
		var bytes = new byte[2];

		byte byte1 = (byte) (u2 >>> 8), byte2 = (byte) u2;

		switch (endianness) {
		case BIG_ENDIAN:
			bytes[0] = byte1;
			bytes[1] = byte2;

			break;
		case LITTLE_ENDIAN:
			bytes[0] = byte2;
			bytes[1] = byte1;

			break;
		default:
			throw new UnsupportedEndiannessException();
		}

		return bytes;
	}

	public int unsignedShortFromBytes(int byte1, int byte2) {
		int u2;

		switch (endianness) {
		case BIG_ENDIAN:
			u2 = (byte1 << 8) + (byte2 << 0);

			break;
		case LITTLE_ENDIAN:
			u2 = (byte2 << 8) + (byte1 << 0);

			break;
		default:
			throw new UnsupportedEndiannessException();
		}

		return u2;
	}

	public short signedShortFromBytes(int byte1, int byte2) {
		return (short) unsignedShortFromBytes(byte1, byte2);
	}

}
