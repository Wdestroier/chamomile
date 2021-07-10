package com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap;

import com.github.wdestroier.chamomile.classfile.attributeinfo.stackmap.frametype.*;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructure;
import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;
import com.github.wdestroier.chamomile.io.MultiEndianOutputStream;

import lombok.Data;

@Data
public class StackMapFrame implements PseudoStructure<StackMapFrame> {

	private SameFrame sameFrame;
	private SameLocals1StackItemFrame sameLocals1StackItemFrame;
	private SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended;
	private ChopFrame chopFrame;
	private SameFrameExtended sameFrameExtended;
	private AppendFrame appendFrame;
	private FullFrame fullFrame;

	@Override
	public StackMapFrame read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		return pseudoStructureFactory.getStackMapFrame(input);
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		if (sameFrame != null) {
			sameFrame.write(output);
		} else if (sameLocals1StackItemFrame != null) {
			sameLocals1StackItemFrame.write(output);
		} else if (sameLocals1StackItemFrameExtended != null) {
			sameLocals1StackItemFrameExtended.write(output);
		} else if (chopFrame != null) {
			chopFrame.write(output);
		} else if (sameFrameExtended != null) {
			sameFrameExtended.write(output);
		} else if (appendFrame != null) {
			appendFrame.write(output);
		} else if (fullFrame != null) {
			fullFrame.write(output);
		}
	}

	public StackMapFrame setSameFrame(SameFrame sameFrame) {
		this.sameFrame = sameFrame;
		sameLocals1StackItemFrame = null;
		sameLocals1StackItemFrameExtended = null;
		chopFrame = null;
		sameFrameExtended = null;
		appendFrame = null;
		fullFrame = null;

		return this;
	}

	public StackMapFrame setSameLocals1StackItemFrame(SameLocals1StackItemFrame sameLocals1StackItemFrame) {
		sameFrame = null;
		this.sameLocals1StackItemFrame = sameLocals1StackItemFrame;
		sameLocals1StackItemFrameExtended = null;
		chopFrame = null;
		sameFrameExtended = null;
		appendFrame = null;
		fullFrame = null;

		return this;
	}

	public StackMapFrame setSameLocals1StackItemFrameExtended(SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended) {
		sameFrame = null;
		sameLocals1StackItemFrame = null;
		this.sameLocals1StackItemFrameExtended = sameLocals1StackItemFrameExtended;
		chopFrame = null;
		sameFrameExtended = null;
		appendFrame = null;
		fullFrame = null;

		return this;
	}

	public StackMapFrame setChopFrame(ChopFrame chopFrame) {
		sameFrame = null;
		sameLocals1StackItemFrame = null;
		sameLocals1StackItemFrameExtended = null;
		this.chopFrame = chopFrame;
		sameFrameExtended = null;
		appendFrame = null;
		fullFrame = null;

		return this;
	}

	public StackMapFrame setSameFrameExtended(SameFrameExtended sameFrameExtended) {
		sameFrame = null;
		sameLocals1StackItemFrame = null;
		sameLocals1StackItemFrameExtended = null;
		chopFrame = null;
		this.sameFrameExtended = sameFrameExtended;
		appendFrame = null;
		fullFrame = null;

		return this;
	}

	public StackMapFrame setAppendFrame(AppendFrame appendFrame) {
		sameFrame = null;
		sameLocals1StackItemFrame = null;
		sameLocals1StackItemFrameExtended = null;
		chopFrame = null;
		sameFrameExtended = null;
		this.appendFrame = appendFrame;
		fullFrame = null;

		return this;
	}

	public StackMapFrame setFullFrame(FullFrame fullFrame) {
		sameFrame = null;
		sameLocals1StackItemFrame = null;
		sameLocals1StackItemFrameExtended = null;
		chopFrame = null;
		sameFrameExtended = null;
		appendFrame = null;
		this.fullFrame = fullFrame;

		return this;
	}

}
