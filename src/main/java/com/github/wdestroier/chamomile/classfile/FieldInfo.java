package com.github.wdestroier.chamomile.classfile;

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
public class FieldInfo implements PseudoStructure<FieldInfo> {

	private int accessFlags;
	private int nameIndex;
	private int descriptorIndex;
	private int attributesCount;
	private PseudoStructure<?>[] attributes;

	@Override
	public FieldInfo read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		accessFlags = input.readUnsignedShort();
		nameIndex = input.readUnsignedShort();
		descriptorIndex = input.readUnsignedShort();
		attributesCount = input.readUnsignedShort();
		attributes = new PseudoStructure<?>[attributesCount];

		for (var i = 0; i < attributesCount; i++) {
			attributes[i] = pseudoStructureFactory.getAttributeInfo(input);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(accessFlags);
		output.writeUnsignedShort(nameIndex);
		output.writeUnsignedShort(descriptorIndex);
		output.writeUnsignedShort(attributesCount);

		for (var attribute : attributes) {
			if (attribute != null) {
				attribute.write(output);
			}
		}
	}

}
