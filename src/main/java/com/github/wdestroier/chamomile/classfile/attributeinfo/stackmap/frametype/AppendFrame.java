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
public class AppendFrame implements PseudoStructure<AppendFrame> {

	private short frameType;
	private int offsetDelta;
	private VerificationTypeInfo[] locals;

	@Override
	public AppendFrame read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		frameType = input.readUnsignedByte();
		offsetDelta = input.readUnsignedShort();
		var localsSize = frameType - 251;
		locals = new VerificationTypeInfo[localsSize];

		for (var i = 0; i < localsSize; i++) {
			locals[i] = pseudoStructureFactory.getVerificationTypeInfo(input);
		}

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedByte(frameType);
		output.writeUnsignedShort(offsetDelta);

		for (var local : locals) {
			local.write(output);
		}
	}

}
