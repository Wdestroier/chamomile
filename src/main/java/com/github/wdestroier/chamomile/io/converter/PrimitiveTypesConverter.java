package com.github.wdestroier.chamomile.io.converter;

import com.github.wdestroier.chamomile.io.Endianness;

import lombok.experimental.Delegate;

public class PrimitiveTypesConverter extends AbstractTypeConverter {

	@Delegate(excludes = AbstractTypeConverter.class)
	private ByteTypeConverter byteTypeConverter;
	@Delegate(excludes = AbstractTypeConverter.class)
	private ShortTypeConverter shortTypeConverter;
	@Delegate(excludes = AbstractTypeConverter.class)
	private IntTypeConverter intTypeConverter;
	@Delegate(excludes = AbstractTypeConverter.class)
	private LongTypeConverter longTypeConverter;

	public PrimitiveTypesConverter(Endianness endianness) {
		super(endianness);

		byteTypeConverter = new ByteTypeConverter(endianness);
		shortTypeConverter = new ShortTypeConverter(endianness);
		intTypeConverter = new IntTypeConverter(endianness);
		longTypeConverter = new LongTypeConverter(endianness);
	}

	@Override
	public Endianness getEndianness() {
		return super.getEndianness();
	}

	@Override
	public void setEndianness(Endianness endianness) {
		super.setEndianness(endianness);

		byteTypeConverter.setEndianness(endianness);
		shortTypeConverter.setEndianness(endianness);
		intTypeConverter.setEndianness(endianness);
		longTypeConverter.setEndianness(endianness);
	}
	
}
