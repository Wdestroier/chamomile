package com.github.wdestroier.chamomile.io.converter;

import com.github.wdestroier.chamomile.io.Endianness;

public class IntTypeConverter extends AbstractTypeConverter {

	public IntTypeConverter(Endianness endianness) {
		super(endianness);
	}

	public byte[] signedIntToBytes(int s4) {
		return unsignedIntToBytes(s4);
	}

	public byte[] unsignedIntToBytes(long u4) {
		var bytes = new byte[4];

		byte byte1 = (byte) (u4 >> 24), byte2 = (byte) (u4 >> 16),
				byte3 = (byte) (u4 >> 8), byte4 = (byte) u4;

		switch (endianness) {
		case BIG_ENDIAN:
			bytes[0] = byte1;
			bytes[1] = byte2;
			bytes[2] = byte3;
			bytes[3] = byte4;

			break;
		case LITTLE_ENDIAN:
			bytes[0] = byte4;
			bytes[1] = byte3;
			bytes[2] = byte2;
			bytes[3] = byte1;

			break;
		default:
			throw new UnsupportedEndiannessException();
		}

		return bytes;
	}

	public int signedIntFromBytes(int byte1, int byte2, int byte3, int byte4) {
		return (int) unsignedIntFromBytes(byte1, byte2, byte3, byte4);
	}

	public long unsignedIntFromBytes(int byte1, int byte2, int byte3, int byte4) {
		long u4;

		switch (endianness) {
		case BIG_ENDIAN:
			u4 = (long) byte1 << 24
				| ((long) byte2 & 0xFF) << 16
				| ((long) byte3 & 0xFF) << 8
				| ((long) byte4 & 0xFF);
			break;
		case LITTLE_ENDIAN:
			u4 = (long) byte4 << 24
				| ((long) byte3 & 0xFF) << 16
				| ((long) byte2 & 0xFF) << 8
				| ((long) byte1 & 0xFF);
			break;
		default:
			throw new UnsupportedEndiannessException();
		}

		return u4;
	}

	public int signedIntFromUnsignedInt(long u4) {
		return (int) u4;
	}

	public long unsignedLongFromSignedInt(int s4) {
		return Integer.toUnsignedLong(s4);
	}

}
