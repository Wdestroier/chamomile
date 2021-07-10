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
public class TypePath implements PseudoStructure<TypePath> {

	private short pathLength;
	private Path[] path;

	@Override
	public TypePath read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		pathLength = input.readUnsignedByte();
		path = new Path[pathLength];

		for (var i = 0; i < pathLength; i++) {
			path[i] = new Path().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(pathLength);

		for (var p : path) {
			p.write(output);
		}
	}

}
