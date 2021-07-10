package com.github.wdestroier.chamomile.classfile.constantkind;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstantMethodHandle implements ConstantKind<ConstantMethodHandle> {

	private short tag;
	private short referenceKind;
	private int referenceIndex;

	@Override
	public ConstantMethodHandle read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tag = input.readUnsignedByte();
		referenceKind = input.readUnsignedByte();
		referenceIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(tag);
		output.writeUnsignedByte(referenceKind);
		output.writeUnsignedShort(referenceIndex);
	}

	public static class ReferenceKind {

		public static final short getField = 1;
		public static final short getStatic = 2;
		public static final short putField = 3;
		public static final short putStatic = 4;
		public static final short invokeVirtual = 5;
		public static final short invokeStatic = 6;
		public static final short invokeSpecial = 7;
		public static final short newInvokeSpecial = 8;
		public static final short invokeInterface = 9;

	}

}
