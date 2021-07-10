package com.github.wdestroier.chamomile.classfile.constantkind;

import static java.util.Map.entry;

import java.util.Map;

import com.github.wdestroier.chamomile.classfile.pseudostructure.PseudoStructureFactory;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ConstantKindFactory {

	private PseudoStructureFactory pseudoStructureFactory;

	private final Map<Class<? extends ConstantKind>, Integer> tagByConstantKind = Map.ofEntries(
			entry(ConstantUtf8.class, 1),
			entry(ConstantClass.class, 7),
			entry(ConstantFieldref.class, 9),
			entry(ConstantMethodref.class, 10),
			entry(ConstantInterfaceMethodref.class, 11),
			entry(ConstantNameAndType.class, 12),
			entry(ConstantString.class, 8),
			entry(ConstantInteger.class, 3),
			entry(ConstantFloat.class, 4),
			entry(ConstantLong.class, 5),
			entry(ConstantDouble.class, 6),
			entry(ConstantMethodHandle.class, 15),
			entry(ConstantMethodType.class, 16),
			entry(ConstantDynamic.class, 17),
			entry(ConstantInvokeDynamic.class, 18),
			entry(ConstantModule.class, 19),
			entry(ConstantPackage.class, 20));

	@SneakyThrows
	public <T extends ConstantKind> T getConstantKind(MultiEndianInputStream data) {
		var tag = data.readUnsignedByte();
		data.unreadUnsignedByte(tag);

		ConstantKind<? extends ConstantKind<?>> constantKind;

		switch (tag) {
		case 1:
			constantKind = new ConstantUtf8();
			break;
		case 7:
			constantKind = new ConstantClass();
			break;
		case 9:
			constantKind = new ConstantFieldref();
			break;
		case 10:
			constantKind = new ConstantMethodref();
			break;
		case 11:
			constantKind = new ConstantInterfaceMethodref();
			break;
		case 12:
			constantKind = new ConstantNameAndType();
			break;
		case 8:
			constantKind = new ConstantString();
			break;
		case 3:
			constantKind = new ConstantInteger();
			break;
		case 4:
			constantKind = new ConstantFloat();
			break;
		case 5:
			constantKind = new ConstantLong();
			break;
		case 6:
			constantKind = new ConstantDouble();
			break;
		case 15:
			constantKind = new ConstantMethodHandle();
			break;
		case 16:
			constantKind = new ConstantMethodType();
			break;
		case 17:
			constantKind = new ConstantDynamic();
			break;
		case 18:
			constantKind = new ConstantInvokeDynamic();
			break;
		case 19:
			constantKind = new ConstantModule();
			break;
		case 20:
			constantKind = new ConstantPackage();
			break;
		default:
			constantKind = null;
			break;
		}

		return (T) constantKind.read(data, pseudoStructureFactory);
	}

	public short getTag(ConstantKind<?> constantKind) {
		return getTag(constantKind.getClass());
	}

	public short getTag(Class<? extends ConstantKind> constantKind) {
		return (short) (int) tagByConstantKind.get(constantKind);
	}

}
