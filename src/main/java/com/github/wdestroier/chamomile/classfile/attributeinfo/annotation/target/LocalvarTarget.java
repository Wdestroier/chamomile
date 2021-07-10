package com.github.wdestroier.chamomile.classfile.attributeinfo.annotation.target;

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
public class LocalvarTarget implements PseudoStructure<LocalvarTarget> {

	private int tableLength;
	private Table[] table;

	@Override
	public LocalvarTarget read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		tableLength = input.readUnsignedShort();
		table = new Table[tableLength];

		for (var i = 0; i < tableLength; i++) {
			table[i] = new Table().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(tableLength);

		for (var t : table) {
			t.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Table implements PseudoStructure<Table> {

		private int startPc;
		private int length;
		private int index;

		@Override
		public Table read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			startPc = input.readUnsignedShort();
			length = input.readUnsignedShort();
			index = input.readUnsignedShort();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedShort(startPc);
			output.writeUnsignedShort(length);
			output.writeUnsignedShort(index);
		}

	}

}
