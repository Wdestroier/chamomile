package com.github.wdestroier.chamomile.classfile.attributeinfo;

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
public class ConstantValueAttribute implements PseudoStructure<ConstantValueAttribute> {

	/**
	 * Must be a valid index into the constant pool to a ConstantUtf8 structure
	 * representing the String "ConstantValue"
	 */
	private int attributeNameIndex;

	/**
	 * This value must be 2.
	 */
	private long attributeLength;

	/**
	 * Must be a valid index into the constant pool table to the appropriated type
	 */
	private int constantValueIndex;

	@Override
	public ConstantValueAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		constantValueIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(constantValueIndex);
	}

}
