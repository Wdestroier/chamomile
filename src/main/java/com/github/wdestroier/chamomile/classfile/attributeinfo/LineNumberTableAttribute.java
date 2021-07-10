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
public class LineNumberTableAttribute implements PseudoStructure<LineNumberTableAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int lineNumberTableLength;
	private LineNumber[] lineNumberTable;

	@Override
	public LineNumberTableAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		lineNumberTableLength = input.readUnsignedShort();
		lineNumberTable = new LineNumber[lineNumberTableLength];

		for (var i = 0; i < lineNumberTableLength; i++) {
			lineNumberTable[i] = new LineNumber().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(lineNumberTableLength);

		for (var lineNumber : lineNumberTable) {
			lineNumber.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LineNumber implements PseudoStructure<LineNumber> {

		private int startPc;
		private int lineNumber;

		@Override
		public LineNumber read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			startPc = input.readUnsignedShort();
			lineNumber = input.readUnsignedShort();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedShort(startPc);
			output.writeUnsignedShort(lineNumber);
		}

	}

}
