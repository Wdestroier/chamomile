package com.github.wdestroier.chamomile.classfile.constantkind;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.Endianness;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;
import com.github.wdestroier.chamomile.io.converter.ByteTypeConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstantUtf8 implements ConstantKind<ConstantUtf8> {

	private static final ByteTypeConverter BYTE_TYPE_CONVERTER = new ByteTypeConverter(Endianness.BIG_ENDIAN);

	private short tag;
	private int length;
	private short[] bytes;

	@Override
	public ConstantUtf8 read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tag = input.readUnsignedByte();
		length = input.readUnsignedShort();
		bytes = input.readUnsignedBytes(length);

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(tag);
		output.writeUnsignedShort(length);
		output.writeUnsignedBytes(bytes);
	}

	public String getBytesString() {
		return new String(BYTE_TYPE_CONVERTER.unsignedBytesToSignedBytes(bytes));
	}

}
