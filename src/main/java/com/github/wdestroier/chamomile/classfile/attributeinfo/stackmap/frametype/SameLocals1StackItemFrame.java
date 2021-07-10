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
public class SameLocals1StackItemFrame implements PseudoStructure<SameLocals1StackItemFrame> {

	private short frameType;
	private VerificationTypeInfo[] stack;

	@Override
	public SameLocals1StackItemFrame read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		frameType = input.readUnsignedByte();
		stack = new VerificationTypeInfo[1];
		stack[0] = pseudoStructureFactory.getVerificationTypeInfo(input);

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(frameType);

		for (var verificationTypeInfo : stack) {
			verificationTypeInfo.write(output);
		}
	}

}
