package com.github.wdestroier.chamomile.io;

public enum Endianness {
	/**
	 * In { 00 11 22 33 } the first byte that is read is 00.
	 */
	LITTLE_ENDIAN,
	/**
	 * In { 00 11 22 33 } the first byte that is read is 33.
	 */
	BIG_ENDIAN;
}
