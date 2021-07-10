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
public class ConstantInvokeDynamic implements ConstantKind<ConstantInvokeDynamic> {

	private short tag;
	private int bootstrapMethodAttrIndex;
	private int nameAndTypeIndex;

	@Override
	public ConstantInvokeDynamic read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tag = input.readUnsignedByte();
		bootstrapMethodAttrIndex = input.readUnsignedShort();
		nameAndTypeIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(tag);
		output.writeUnsignedShort(bootstrapMethodAttrIndex);
		output.writeUnsignedShort(nameAndTypeIndex);
	}

}
