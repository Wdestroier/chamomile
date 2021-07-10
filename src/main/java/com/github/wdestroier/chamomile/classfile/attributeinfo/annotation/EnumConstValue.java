package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnumConstValue implements PseudoStructure<EnumConstValue> {

	private int typeNameIndex;
	private int constNameIndex;

	@Override
	public EnumConstValue read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		typeNameIndex = input.readUnsignedShort();
		constNameIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedInt(typeNameIndex);
		output.writeUnsignedInt(constNameIndex);
	}

}
