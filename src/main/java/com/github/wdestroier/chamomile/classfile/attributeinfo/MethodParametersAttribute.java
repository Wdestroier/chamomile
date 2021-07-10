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
public class MethodParametersAttribute implements PseudoStructure<MethodParametersAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private short parametersCount;
	private Parameter[] parameters;

	@Override
	public MethodParametersAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		parametersCount = input.readUnsignedByte();
		parameters = new Parameter[parametersCount];

		for (var i = 0; i < parametersCount; i++) {
			parameters[i] = new Parameter().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(attributeNameIndex);

		for (var parameter : parameters) {
			parameter.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Parameter implements PseudoStructure<Parameter> {

		private int nameIndex;
		private int accessFlags;

		@Override
		public Parameter read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			nameIndex = input.readUnsignedShort();
			accessFlags = input.readUnsignedShort();

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedShort(nameIndex);
			output.writeUnsignedShort(accessFlags);
		}

	}

}
