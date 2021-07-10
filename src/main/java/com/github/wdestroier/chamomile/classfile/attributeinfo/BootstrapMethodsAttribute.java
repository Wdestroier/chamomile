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
public class BootstrapMethodsAttribute implements PseudoStructure<BootstrapMethodsAttribute> {

	private int attributeNameIndex;
	private long attributeLength;
	private int numBootstrapMethods;
	private BootstrapMethod[] bootstrapMethods;

	@Override
	public BootstrapMethodsAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		numBootstrapMethods = input.readUnsignedShort();
		bootstrapMethods = new BootstrapMethod[numBootstrapMethods];

		for (var i = 0; i < numBootstrapMethods; i++) {
			bootstrapMethods[i] = new BootstrapMethod().read(input, pseudoStructureFactory);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedShort(numBootstrapMethods);

		for (var bootstrapMethod : bootstrapMethods) {
			bootstrapMethod.write(output);
		}
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BootstrapMethod implements PseudoStructure<BootstrapMethod> {

		private int bootstrapMethodRef;
		private int numBootstrapArguments;
		private int[] bootstrapArguments;

		@Override
		public BootstrapMethod read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
			bootstrapMethodRef = input.readUnsignedShort();
			numBootstrapArguments = input.readUnsignedShort();
			bootstrapArguments = new int[numBootstrapArguments];

			for (var i = 0; i < numBootstrapArguments; i++) {
				bootstrapArguments[i] = input.readUnsignedShort();
			}

			return this;
		}

		@Override
		public void write(MultiEndianOutputStream output) {
			output.writeUnsignedShort(bootstrapMethodRef);
			output.writeUnsignedShort(numBootstrapArguments);

			for (var bootstrapArgument : bootstrapArguments) {
				output.writeUnsignedShort(bootstrapArgument);
			}
		}

	}

}
