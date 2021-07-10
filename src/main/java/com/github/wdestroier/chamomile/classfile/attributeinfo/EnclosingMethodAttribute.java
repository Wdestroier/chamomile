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
public class EnclosingMethodAttribute implements PseudoStructure<EnclosingMethodAttribute> {

	private int attributeNameIndex;

	/**
	 * The value of the attribute length must be four.
	 */
	private long attributeLength;

	private int classIndex;
	private int methodIndex;

	@Override
	public EnclosingMethodAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		classIndex = input.readUnsignedShort();
		methodIndex = input.readUnsignedShort();

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(classIndex);
		output.writeUnsignedShort(methodIndex);
	}

}
