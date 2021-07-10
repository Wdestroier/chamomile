package com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap;

import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.AppendFrame;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.ChopFrame;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.FullFrame;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.SameFrame;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.SameFrameExtended;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.SameLocals1StackItemFrame;
import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.SameLocals1StackItemFrameExtended;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
@AllArgsConstructor
public class StackMapFrameFactory {

	private PseudoStructureFactory pseudoStructureFactory;

	@SneakyThrows
	public StackMapFrame getStackMapFrame(MultiEndianInputStream input) {
		var frameType = input.readUnsignedByte();
		input.unreadUnsignedByte(frameType);

		var stackMapFrame = new StackMapFrame();

		if (frameType >= 0 && frameType <= 63) {
			stackMapFrame.setSameFrame(new SameFrame().read(input, pseudoStructureFactory));
		} else if (frameType >= 64 && frameType <= 127) {
			stackMapFrame.setSameLocals1StackItemFrame(
					new SameLocals1StackItemFrame().read(input, pseudoStructureFactory));
		} else if (frameType >= 128 && frameType <= 246) {
			// Reserved for future use
			stackMapFrame = null;
		} else if (frameType == 247) {
			stackMapFrame.setSameLocals1StackItemFrameExtended(
					new SameLocals1StackItemFrameExtended().read(input, pseudoStructureFactory));
		} else if (frameType >= 248 && frameType <= 250) {
			stackMapFrame.setChopFrame(new ChopFrame().read(input, pseudoStructureFactory));
		} else if (frameType == 251) {
			stackMapFrame.setSameFrameExtended(new SameFrameExtended().read(input, pseudoStructureFactory));
		} else if (frameType >= 252 && frameType <= 254) {
			stackMapFrame.setAppendFrame(new AppendFrame().read(input, pseudoStructureFactory));
		} else if (frameType == 255) {
			stackMapFrame.setFullFrame(new FullFrame().read(input, pseudoStructureFactory));
		} else {
			stackMapFrame = null;
		}

		return stackMapFrame;
	}

}
