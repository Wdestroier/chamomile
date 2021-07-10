package com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype;

import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.verificationtype.VerificationTypeInfo;
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
public class FullFrame implements PseudoStructure<FullFrame> {

	private short frameType;
	private int offsetDelta;
	private int numberOfLocals;
	private VerificationTypeInfo[] locals;
	private int numberOfStackItems;
	private VerificationTypeInfo[] stack;

	@Override
	public FullFrame read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		frameType = input.readUnsignedByte();
		offsetDelta = input.readUnsignedShort();
		numberOfLocals = input.readUnsignedShort();
		locals = new VerificationTypeInfo[numberOfLocals];

		for (var i = 0; i < numberOfLocals; i++) {
			locals[i] = pseudoStructureFactory.getVerificationTypeInfo(input);
		}

		numberOfStackItems = input.readUnsignedShort();
		stack = new VerificationTypeInfo[numberOfStackItems];

		for (var i = 0; i < numberOfStackItems; i++) {
			stack[i] = pseudoStructureFactory.getVerificationTypeInfo(input);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(frameType);
		output.writeUnsignedShort(offsetDelta);
		output.writeUnsignedShort(numberOfLocals);

		for (var local : locals) {
			local.write(output);
		}

		output.writeUnsignedShort(numberOfStackItems);

		for (var verificationTypeInfo : stack) {
			verificationTypeInfo.write(output);
		}
	}

}
