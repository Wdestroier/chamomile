package com.github.wdestroier.chamomile.io.converter;

import com.github.wdestroier.chamomile.io.Endianness;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractTypeConverter {

	@NonNull
	protected Endianness endianness;

}
