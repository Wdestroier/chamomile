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
public class TypeAnnotation implements PseudoStructure<TypeAnnotation> {

	private short targetType;
	private TargetInfo targetInfo;
	private TypePath targetPath;
	private int typeIndex;
	private int numElementValuePairs;
	private ElementValuePair[] elementValuePairs;

	@Override
	public TypeAnnotation read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		targetType = input.readUnsignedByte();
		input.unreadUnsignedByte(targetType);
		targetInfo = pseudoStructureFactory.getTargetInfo(input);
		targetPath = new TypePath().read(input, pseudoStructureFactory);
		typeIndex = input.readUnsignedShort();
		numElementValuePairs = input.readUnsignedShort();
		elementValuePairs = new ElementValuePair[numElementValuePairs];

		for (var i = 0; i < numElementValuePairs; i++) {
			elementValuePairs[i] = new ElementValuePair().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(targetType);
		targetInfo.write(output);
		targetPath.write(output);
		output.writeUnsignedShort(typeIndex);
		output.writeUnsignedShort(numElementValuePairs);

		for (var elementValuePair : elementValuePairs) {
			elementValuePair.write(output);
		}
	}

}
