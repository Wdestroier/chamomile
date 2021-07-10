package com.github.wdestroier.chamomile.classfile.attributeinfo;

import com.github.wdestroier.chamomile.classfile.attributeinfo.module.Exports;
import com.github.wdestroier.chamomile.classfile.attributeinfo.module.Opens;
import com.github.wdestroier.chamomile.classfile.attributeinfo.module.Provides;
import com.github.wdestroier.chamomile.classfile.attributeinfo.module.Requires;
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
public class ModuleAttribute implements PseudoStructure<ModuleAttribute> {

	private int attributeNameIndex;
	private long attributeLength;

	private int moduleNameIndex;
	private int moduleFlags;
	private int moduleVersionIndex;

	private int requiresCount;
	private Requires[] requires;

	private int exportsCount;
	private Exports[] exports;

	private int opensCount;
	private Opens[] opens;

	private int usesCount;
	private int[] usesIndex;

	private int providesCount;
	private Provides[] provides;

	@Override
	public ModuleAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();

		moduleNameIndex = input.readUnsignedShort();
		moduleFlags = input.readUnsignedShort();
		moduleVersionIndex = input.readUnsignedShort();

		requiresCount = input.readUnsignedShort();
		requires = new Requires[requiresCount];

		for (var i = 0; i < requiresCount; i++) {
			requires[i] = new Requires().read(input, pseudoStructureFactory);
		}

		exportsCount = input.readUnsignedShort();
		exports = new Exports[exportsCount];

		for (var i = 0; i < exportsCount; i++) {
			exports[i] = new Exports().read(input, pseudoStructureFactory);
		}

		opensCount = input.readUnsignedShort();
		opens = new Opens[opensCount];

		for (var i = 0; i < opensCount; i++) {
			opens[i] = new Opens().read(input, pseudoStructureFactory);
		}

		usesCount = input.readUnsignedShort();
		usesIndex = new int[usesCount];

		for (var i = 0; i < usesCount; i++) {
			usesIndex[i] = input.readUnsignedShort();
		}

		providesCount = input.readUnsignedShort();
		provides = new Provides[providesCount];

		for (var i = 0; i < providesCount; i++) {
			provides[i] = new Provides().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);

		output.writeUnsignedShort(moduleNameIndex);
		output.writeUnsignedShort(moduleFlags);
		output.writeUnsignedShort(moduleVersionIndex);

		output.writeUnsignedShort(requiresCount);

		for (var r : requires) {
			r.write(output);
		}

		output.writeUnsignedShort(exportsCount);

		for (var e : exports) {
			e.write(output);
		}

		output.writeUnsignedShort(opensCount);

		for (var o : opens) {
			o.write(output);
		}

		output.writeUnsignedShort(usesCount);

		for (var i : usesIndex) {
			output.writeUnsignedShort(i);
		}

		output.writeUnsignedShort(providesCount);

		for (var p : provides) {
			p.write(output);
		}
	}

}
