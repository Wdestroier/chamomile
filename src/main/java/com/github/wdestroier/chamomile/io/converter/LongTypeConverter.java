package com.github.wdestroier.chamomile.io.converter;

import java.math.BigInteger;

import com.github.wdestroier.chamomile.io.Endianness;

public class LongTypeConverter extends AbstractTypeConverter {

	public LongTypeConverter(Endianness endianness) {
		super(endianness);
	}

	public byte[] signedLongToBytes(long s8) {
		var bytes = new byte[8];

		byte byte1 = (byte) (s8 >>> 56), byte2 = (byte) (s8 >>> 48), byte3 = (byte) (s8 >>> 40),
				byte4 = (byte) (s8 >>> 32), byte5 = (byte) (s8 >>> 24), byte6 = (byte) (s8 >>> 16),
						byte7 = (byte) (s8 >>> 8), byte8 = (byte) (s8 >>> 0);

		switch (endianness) {
		case BIG_ENDIAN:
			bytes[0] = byte1;
			bytes[1] = byte2;
			bytes[2] = byte3;
			bytes[3] = byte4;
			bytes[4] = byte5;
			bytes[5] = byte6;
			bytes[6] = byte7;
			bytes[7] = byte8;

			break;
		case LITTLE_ENDIAN:
			bytes[0] = byte8;
			bytes[1] = byte7;
			bytes[2] = byte6;
			bytes[3] = byte5;
			bytes[4] = byte4;
			bytes[5] = byte3;
			bytes[6] = byte2;
			bytes[7] = byte1;

			break;
		default:
			throw new UnsupportedEndiannessException();
		}

		return bytes;
	}

	public byte[] unsignedLongToBytes(BigInteger u8) {
		return signedLongToBytes(u8.longValue());
	}

	public long signedLongFromBytes(int byte1, int byte2, int byte3, int byte4, int byte5,
			int byte6, int byte7, int byte8) {
		long s8;

		switch (endianness) {
		case BIG_ENDIAN:
			s8 = (((long) byte1 << 56) + ((long) (byte2 & 0xFF) << 48)
					+ ((long) (byte3 & 0xFF) << 40) + ((long) (byte4 & 0xFF) << 32)
					+ ((long) (byte5 & 0xFF) << 24) + ((byte6 & 0xFF) << 16)
					+ ((byte7 & 0xFF) << 8) + ((byte8 & 0xFF) << 0));

			break;
		case LITTLE_ENDIAN:
			s8 = (((long) byte8 << 56) + ((long) (byte7 & 0xFF) << 48) +
					((long) (byte6 & 0xFF) << 40) + ((long) (byte5 & 0xFF) << 32)
					+ ((long) (byte4 & 0xFF) << 24) + ((byte3 & 0xFF) << 16)
					+ ((byte2 & 0xFF) << 8) + ((byte1 & 0xFF) << 0));

			break;
		default:
			throw new UnsupportedEndiannessException();
		}

		return s8;
	}

	public BigInteger unsignedLongFromBytes(int byte1, int byte2, int byte3, int byte4,
			int byte5, int byte6, int byte7, int byte8) {
		var bytes = new byte[8];

		switch (endianness) {
		case BIG_ENDIAN:
			bytes[0] = (byte) byte1;
			bytes[1] = (byte) byte2;
			bytes[2] = (byte) byte3;
			bytes[3] = (byte) byte4;
			bytes[4] = (byte) byte5;
			bytes[5] = (byte) byte6;
			bytes[6] = (byte) byte7;
			bytes[7] = (byte) byte8;

			break;
		case LITTLE_ENDIAN:
			bytes[0] = (byte) byte8;
			bytes[1] = (byte) byte7;
			bytes[2] = (byte) byte6;
			bytes[3] = (byte) byte5;
			bytes[4] = (byte) byte4;
			bytes[5] = (byte) byte3;
			bytes[6] = (byte) byte2;
			bytes[7] = (byte) byte1;

			break;
		default:
			throw new UnsupportedEndiannessException();
		}

		return new BigInteger(bytes);
	}

}
