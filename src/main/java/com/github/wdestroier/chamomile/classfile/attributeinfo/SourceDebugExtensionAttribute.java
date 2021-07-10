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
public class SourceDebugExtensionAttribute implements PseudoStructure<SourceDebugExtensionAttribute> {

	private int attributeNameIndex;
	private long attributeLength;

	/**
	 * Holds extended debugging information which has no semantic effect on the JVM.
	 * The information is represented using a modified UTF-8 string with no
	 * terminating zero byte. It may denote a string longer than that which can be
	 * represented with an instance of the String class.
	 * 
	 * https://stackoverflow.com/questions/816142/strings-maximum-length-in-java-calling-length-method/42289203#:~:text=String%20is%20considered%20as%20char,String%20in%20java%20is%202147483647.
	 */
	private short[] debugExtension;

	@Override
	public SourceDebugExtensionAttribute read(MultiEndianInputStream input, PseudoStructureFactory pseudoStructureFactory) {
		attributeNameIndex = input.readUnsignedShort();
		attributeLength = input.readUnsignedInt();
		debugExtension = input.readUnsignedBytes((int) attributeLength);

		return this;
	}

	@Override
	public void write(MultiEndianOutputStream output) {
		output.writeUnsignedShort(attributeNameIndex);
		output.writeUnsignedInt(attributeLength);
		output.writeUnsignedBytes(debugExtension);
	}

}
