package com.github.wdestroier.chamomile.classfile.attributeinfo;

import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.StackMapFrame;
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
public class StackMapTableAttribute implements PseudoStructure<StackMapTableAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int numberOfEntries;
	private StackMapFrame[] entries;

	@Override
	public StackMapTableAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numberOfEntries = input.readUnsignedShort();
		entries = new StackMapFrame[numberOfEntries];

		for (var i = 0; i < numberOfEntries; i++) {
			entries[i] = pseudoStructureFactory.getStackMapFrame(input);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(numberOfEntries);
		
		for (var entry : entries) {
			entry.write(output);
		}
	}

}
