package com.github.wdestroier.chamomile.instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.github.wdestroier.chamomile.instruction.comparision.acmp.IfAcmpeqInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.acmp.IfAcmpneInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.cmp0.IfeqInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.cmp0.IfgeInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.cmp0.IfgtInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.cmp0.IfleInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.cmp0.IfltInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.cmp0.IfneInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.dcmp.DcmpgInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.dcmp.DcmplInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.fcmp.FcmpgInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.fcmp.FcmplInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpeqInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpgeInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpgtInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpleInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpltInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.icmp.IfIcmpneInstruction;
import com.github.wdestroier.chamomile.instruction.comparision.lcmp.LcmpInstruction;
import com.github.wdestroier.chamomile.instruction.constant.AconstNullInstruction;
import com.github.wdestroier.chamomile.instruction.constant.BipushInstruction;
import com.github.wdestroier.chamomile.instruction.constant.Ldc2WInstruction;
import com.github.wdestroier.chamomile.instruction.constant.LdcInstruction;
import com.github.wdestroier.chamomile.instruction.constant.LdcWInstruction;
import com.github.wdestroier.chamomile.instruction.constant.NopInstruction;
import com.github.wdestroier.chamomile.instruction.constant.SipushInstruction;
import com.github.wdestroier.chamomile.instruction.constant.dconst.Dconst0Instruction;
import com.github.wdestroier.chamomile.instruction.constant.dconst.Dconst1Instruction;
import com.github.wdestroier.chamomile.instruction.constant.fconst.Fconst0Instruction;
import com.github.wdestroier.chamomile.instruction.constant.fconst.Fconst1Instruction;
import com.github.wdestroier.chamomile.instruction.constant.fconst.Fconst2Instruction;
import com.github.wdestroier.chamomile.instruction.constant.iconst.Iconst0Instruction;
import com.github.wdestroier.chamomile.instruction.constant.iconst.Iconst1Instruction;
import com.github.wdestroier.chamomile.instruction.constant.iconst.Iconst2Instruction;
import com.github.wdestroier.chamomile.instruction.constant.iconst.Iconst3Instruction;
import com.github.wdestroier.chamomile.instruction.constant.iconst.Iconst4Instruction;
import com.github.wdestroier.chamomile.instruction.constant.iconst.Iconst5Instruction;
import com.github.wdestroier.chamomile.instruction.constant.iconst.IconstM1Instruction;
import com.github.wdestroier.chamomile.instruction.constant.lconst.Lconst0Instruction;
import com.github.wdestroier.chamomile.instruction.constant.lconst.Lconst1Instruction;
import com.github.wdestroier.chamomile.instruction.control.AreturnInstruction;
import com.github.wdestroier.chamomile.instruction.control.DreturnInstruction;
import com.github.wdestroier.chamomile.instruction.control.FreturnInstruction;
import com.github.wdestroier.chamomile.instruction.control.GotoInstruction;
import com.github.wdestroier.chamomile.instruction.control.IreturnInstruction;
import com.github.wdestroier.chamomile.instruction.control.JsrInstruction;
import com.github.wdestroier.chamomile.instruction.control.LookupswitchInstruction;
import com.github.wdestroier.chamomile.instruction.control.LreturnInstruction;
import com.github.wdestroier.chamomile.instruction.control.RetInstruction;
import com.github.wdestroier.chamomile.instruction.control.ReturnInstruction;
import com.github.wdestroier.chamomile.instruction.control.TableswitchInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.doubles.D2fInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.doubles.D2iInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.doubles.D2lInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.floats.F2dInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.floats.F2iInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.floats.F2lInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.ints.I2bInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.ints.I2cInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.ints.I2dInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.ints.I2fInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.ints.I2lInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.ints.I2sInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.longs.L2dInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.longs.L2fInstruction;
import com.github.wdestroier.chamomile.instruction.conversion.longs.L2iInstruction;
import com.github.wdestroier.chamomile.instruction.extended.GotoWInstruction;
import com.github.wdestroier.chamomile.instruction.extended.IfnonnullInstruction;
import com.github.wdestroier.chamomile.instruction.extended.IfnullInstruction;
import com.github.wdestroier.chamomile.instruction.extended.JsrWInstruction;
import com.github.wdestroier.chamomile.instruction.extended.MultianewarrayInstruction;
import com.github.wdestroier.chamomile.instruction.extended.WideInstruction;
import com.github.wdestroier.chamomile.instruction.load.AaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.BaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.CaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.DaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.FaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.IaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.LaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.SaloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.aload.Aload0Instruction;
import com.github.wdestroier.chamomile.instruction.load.aload.Aload1Instruction;
import com.github.wdestroier.chamomile.instruction.load.aload.Aload2Instruction;
import com.github.wdestroier.chamomile.instruction.load.aload.Aload3Instruction;
import com.github.wdestroier.chamomile.instruction.load.aload.AloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.dload.Dload0Instruction;
import com.github.wdestroier.chamomile.instruction.load.dload.Dload1Instruction;
import com.github.wdestroier.chamomile.instruction.load.dload.Dload2Instruction;
import com.github.wdestroier.chamomile.instruction.load.dload.Dload3Instruction;
import com.github.wdestroier.chamomile.instruction.load.dload.DloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.fload.Fload0Instruction;
import com.github.wdestroier.chamomile.instruction.load.fload.Fload1Instruction;
import com.github.wdestroier.chamomile.instruction.load.fload.Fload2Instruction;
import com.github.wdestroier.chamomile.instruction.load.fload.Fload3Instruction;
import com.github.wdestroier.chamomile.instruction.load.fload.FloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.iload.Iload0Instruction;
import com.github.wdestroier.chamomile.instruction.load.iload.Iload1Instruction;
import com.github.wdestroier.chamomile.instruction.load.iload.Iload2Instruction;
import com.github.wdestroier.chamomile.instruction.load.iload.Iload3Instruction;
import com.github.wdestroier.chamomile.instruction.load.iload.IloadInstruction;
import com.github.wdestroier.chamomile.instruction.load.lload.Lload0Instruction;
import com.github.wdestroier.chamomile.instruction.load.lload.Lload1Instruction;
import com.github.wdestroier.chamomile.instruction.load.lload.Lload2Instruction;
import com.github.wdestroier.chamomile.instruction.load.lload.Lload3Instruction;
import com.github.wdestroier.chamomile.instruction.load.lload.LloadInstruction;
import com.github.wdestroier.chamomile.instruction.math.IincInstruction;
import com.github.wdestroier.chamomile.instruction.math.add.DaddInstruction;
import com.github.wdestroier.chamomile.instruction.math.add.FaddInstruction;
import com.github.wdestroier.chamomile.instruction.math.add.IaddInstruction;
import com.github.wdestroier.chamomile.instruction.math.add.LaddInstruction;
import com.github.wdestroier.chamomile.instruction.math.and.IandInstruction;
import com.github.wdestroier.chamomile.instruction.math.and.LandInstruction;
import com.github.wdestroier.chamomile.instruction.math.div.DdivInstruction;
import com.github.wdestroier.chamomile.instruction.math.div.FdivInstruction;
import com.github.wdestroier.chamomile.instruction.math.div.IdivInstruction;
import com.github.wdestroier.chamomile.instruction.math.div.LdivInstruction;
import com.github.wdestroier.chamomile.instruction.math.mul.DmulInstruction;
import com.github.wdestroier.chamomile.instruction.math.mul.FmulInstruction;
import com.github.wdestroier.chamomile.instruction.math.mul.ImulInstruction;
import com.github.wdestroier.chamomile.instruction.math.mul.LmulInstruction;
import com.github.wdestroier.chamomile.instruction.math.neg.DnegInstruction;
import com.github.wdestroier.chamomile.instruction.math.neg.FnegInstruction;
import com.github.wdestroier.chamomile.instruction.math.neg.InegInstruction;
import com.github.wdestroier.chamomile.instruction.math.neg.LnegInstruction;
import com.github.wdestroier.chamomile.instruction.math.or.IorInstruction;
import com.github.wdestroier.chamomile.instruction.math.or.LorInstruction;
import com.github.wdestroier.chamomile.instruction.math.rem.DremInstruction;
import com.github.wdestroier.chamomile.instruction.math.rem.FremInstruction;
import com.github.wdestroier.chamomile.instruction.math.rem.IremInstruction;
import com.github.wdestroier.chamomile.instruction.math.rem.LremInstruction;
import com.github.wdestroier.chamomile.instruction.math.shift.arithmetic.IshlInstruction;
import com.github.wdestroier.chamomile.instruction.math.shift.arithmetic.IshrInstruction;
import com.github.wdestroier.chamomile.instruction.math.shift.arithmetic.LshlInstruction;
import com.github.wdestroier.chamomile.instruction.math.shift.arithmetic.LshrInstruction;
import com.github.wdestroier.chamomile.instruction.math.shift.logical.IushrInstruction;
import com.github.wdestroier.chamomile.instruction.math.shift.logical.LushrInstruction;
import com.github.wdestroier.chamomile.instruction.math.sub.DsubInstruction;
import com.github.wdestroier.chamomile.instruction.math.sub.FsubInstruction;
import com.github.wdestroier.chamomile.instruction.math.sub.IsubInstruction;
import com.github.wdestroier.chamomile.instruction.math.sub.LsubInstruction;
import com.github.wdestroier.chamomile.instruction.math.xor.IxorInstruction;
import com.github.wdestroier.chamomile.instruction.math.xor.LxorInstruction;
import com.github.wdestroier.chamomile.instruction.reference.AnewarrayInstruction;
import com.github.wdestroier.chamomile.instruction.reference.ArraylengthInstruction;
import com.github.wdestroier.chamomile.instruction.reference.AthrowInstruction;
import com.github.wdestroier.chamomile.instruction.reference.CheckcastInstruction;
import com.github.wdestroier.chamomile.instruction.reference.GetfieldInstruction;
import com.github.wdestroier.chamomile.instruction.reference.GetstaticInstruction;
import com.github.wdestroier.chamomile.instruction.reference.InstanceofInstruction;
import com.github.wdestroier.chamomile.instruction.reference.InvokedynamicInstruction;
import com.github.wdestroier.chamomile.instruction.reference.InvokeinterfaceInstruction;
import com.github.wdestroier.chamomile.instruction.reference.InvokespecialInstruction;
import com.github.wdestroier.chamomile.instruction.reference.InvokestaticInstruction;
import com.github.wdestroier.chamomile.instruction.reference.InvokevirtualInstruction;
import com.github.wdestroier.chamomile.instruction.reference.MonitorenterInstruction;
import com.github.wdestroier.chamomile.instruction.reference.MonitorexitInstruction;
import com.github.wdestroier.chamomile.instruction.reference.NewInstruction;
import com.github.wdestroier.chamomile.instruction.reference.NewarrayInstruction;
import com.github.wdestroier.chamomile.instruction.reference.PutfieldInstruction;
import com.github.wdestroier.chamomile.instruction.reference.PutstaticInstruction;
import com.github.wdestroier.chamomile.instruction.reserved.BreakpointInstruction;
import com.github.wdestroier.chamomile.instruction.reserved.Impdep1Instruction;
import com.github.wdestroier.chamomile.instruction.reserved.Impdep2Instruction;
import com.github.wdestroier.chamomile.instruction.stack.Dup2Instruction;
import com.github.wdestroier.chamomile.instruction.stack.Dup2X1Instruction;
import com.github.wdestroier.chamomile.instruction.stack.Dup2X2Instruction;
import com.github.wdestroier.chamomile.instruction.stack.DupInstruction;
import com.github.wdestroier.chamomile.instruction.stack.DupX1Instruction;
import com.github.wdestroier.chamomile.instruction.stack.DupX2Instruction;
import com.github.wdestroier.chamomile.instruction.stack.Pop2Instruction;
import com.github.wdestroier.chamomile.instruction.stack.PopInstruction;
import com.github.wdestroier.chamomile.instruction.stack.SwapInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.AastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.BastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.CastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.DastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.FastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.IastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.LastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.arraystore.SastoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.astore.Astore0Instruction;
import com.github.wdestroier.chamomile.instruction.store.astore.Astore1Instruction;
import com.github.wdestroier.chamomile.instruction.store.astore.Astore2Instruction;
import com.github.wdestroier.chamomile.instruction.store.astore.Astore3Instruction;
import com.github.wdestroier.chamomile.instruction.store.astore.AstoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.dstore.Dstore0Instruction;
import com.github.wdestroier.chamomile.instruction.store.dstore.Dstore1Instruction;
import com.github.wdestroier.chamomile.instruction.store.dstore.Dstore2Instruction;
import com.github.wdestroier.chamomile.instruction.store.dstore.Dstore3Instruction;
import com.github.wdestroier.chamomile.instruction.store.dstore.DstoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.fstore.Fstore0Instruction;
import com.github.wdestroier.chamomile.instruction.store.fstore.Fstore1Instruction;
import com.github.wdestroier.chamomile.instruction.store.fstore.Fstore2Instruction;
import com.github.wdestroier.chamomile.instruction.store.fstore.Fstore3Instruction;
import com.github.wdestroier.chamomile.instruction.store.fstore.FstoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.istore.Istore0Instruction;
import com.github.wdestroier.chamomile.instruction.store.istore.Istore1Instruction;
import com.github.wdestroier.chamomile.instruction.store.istore.Istore2Instruction;
import com.github.wdestroier.chamomile.instruction.store.istore.Istore3Instruction;
import com.github.wdestroier.chamomile.instruction.store.istore.IstoreInstruction;
import com.github.wdestroier.chamomile.instruction.store.lstore.Lstore0Instruction;
import com.github.wdestroier.chamomile.instruction.store.lstore.Lstore1Instruction;
import com.github.wdestroier.chamomile.instruction.store.lstore.Lstore2Instruction;
import com.github.wdestroier.chamomile.instruction.store.lstore.Lstore3Instruction;
import com.github.wdestroier.chamomile.instruction.store.lstore.LstoreInstruction;
import com.github.wdestroier.chamomile.io.MultiEndianInputStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@SuppressWarnings("unchecked")
public class InstructionFactory {

	public static InstructionFactory instance = new InstructionFactory();

	private Map<String, Short> opcodeFromMnemonic;
	private Map<Short, String> mnemonicFromOpcode;
	private Map<Short, Supplier<Instruction<?>>> supplierFromOpcode;
	private Map<Short, Class<? extends Instruction<?>>> classFromOpcode;
	private Map<Class<? extends Instruction<?>>, Short> opcodeFromClass;

	public InstructionFactory() {
		var list = new ArrayList<InstructionMapping>() {
			private static final long serialVersionUID = 1L;

			public void add(int opcode, String mnemonic, Class<? extends Instruction<?>> instructionClass, Supplier<Instruction<?>> instructionInstanceGenerator) {
				add(new InstructionMapping((short) opcode, mnemonic, instructionClass, instructionInstanceGenerator));
			}
		};

		// Constants
		list.add(0x00, "nop", NopInstruction.class, () -> new NopInstruction());
		list.add(0x01, "aconst_null", AconstNullInstruction.class, () -> new AconstNullInstruction());
		list.add(0x02, "iconst_m1", IconstM1Instruction.class, () -> new IconstM1Instruction());
		list.add(0x03, "iconst_0", Iconst0Instruction.class, () -> new Iconst0Instruction());
		list.add(0x04, "iconst_1", Iconst1Instruction.class, () -> new Iconst1Instruction());
		list.add(0x05, "iconst_2", Iconst2Instruction.class, () -> new Iconst2Instruction());
		list.add(0x06, "iconst_3", Iconst3Instruction.class, () -> new Iconst3Instruction());
		list.add(0x07, "iconst_4", Iconst4Instruction.class, () -> new Iconst4Instruction());
		list.add(0x08, "iconst_5", Iconst5Instruction.class, () -> new Iconst5Instruction());
		list.add(0x09, "lconst_0", Lconst0Instruction.class, () -> new Lconst0Instruction());
		list.add(0x0A, "lconst_1", Lconst1Instruction.class, () -> new Lconst1Instruction());
		list.add(0x0B, "fconst_0", Fconst0Instruction.class, () -> new Fconst0Instruction());
		list.add(0x0C, "fconst_1", Fconst1Instruction.class, () -> new Fconst1Instruction());
		list.add(0x0D, "fconst_2", Fconst2Instruction.class, () -> new Fconst2Instruction());
		list.add(0x0E, "dconst_0", Dconst0Instruction.class, () -> new Dconst0Instruction());
		list.add(0x0F, "dconst_1", Dconst1Instruction.class, () -> new Dconst1Instruction());
		list.add(0x10, "bipush", BipushInstruction.class, () -> new BipushInstruction());
		list.add(0x11, "sipush", SipushInstruction.class, () -> new SipushInstruction());
		list.add(0x12, "ldc", LdcInstruction.class, () -> new LdcInstruction());
		list.add(0x13, "ldc_w", LdcWInstruction.class, () -> new LdcWInstruction());
		list.add(0x14, "ldc2_w", Ldc2WInstruction.class, () -> new Ldc2WInstruction());

		// Loads
		list.add(0x15, "iload", IloadInstruction.class, () -> new IloadInstruction());
		list.add(0x16, "lload", LloadInstruction.class, () -> new LloadInstruction());
		list.add(0x17, "fload", FloadInstruction.class, () -> new FloadInstruction());
		list.add(0x18, "dload", DloadInstruction.class, () -> new DloadInstruction());
		list.add(0x19, "aload", AloadInstruction.class, () -> new AloadInstruction());
		list.add(0x1A, "iload_0", Iload0Instruction.class, () -> new Iload0Instruction());
		list.add(0x1B, "iload_1", Iload1Instruction.class, () -> new Iload1Instruction());
		list.add(0x1C, "iload_2", Iload2Instruction.class, () -> new Iload2Instruction());
		list.add(0x1D, "iload_3", Iload3Instruction.class, () -> new Iload3Instruction());
		list.add(0x1E, "lload_0", Lload0Instruction.class, () -> new Lload0Instruction());
		list.add(0x1F, "lload_1", Lload1Instruction.class, () -> new Lload1Instruction());
		list.add(0x20, "lload_2", Lload2Instruction.class, () -> new Lload2Instruction());
		list.add(0x21, "lload_3", Lload3Instruction.class, () -> new Lload3Instruction());
		list.add(0x22, "fload_0", Fload0Instruction.class, () -> new Fload0Instruction());
		list.add(0x23, "fload_1", Fload1Instruction.class, () -> new Fload1Instruction());
		list.add(0x24, "fload_2", Fload2Instruction.class, () -> new Fload2Instruction());
		list.add(0x25, "fload_3", Fload3Instruction.class, () -> new Fload3Instruction());
		list.add(0x26, "dload_0", Dload0Instruction.class, () -> new Dload0Instruction());
		list.add(0x27, "dload_1", Dload1Instruction.class, () -> new Dload1Instruction());
		list.add(0x28, "dload_2", Dload2Instruction.class, () -> new Dload2Instruction());
		list.add(0x29, "dload_3", Dload3Instruction.class, () -> new Dload3Instruction());
		list.add(0x2A, "aload_0", Aload0Instruction.class, () -> new Aload0Instruction());
		list.add(0x2B, "aload_1", Aload1Instruction.class, () -> new Aload1Instruction());
		list.add(0x2C, "aload_2", Aload2Instruction.class, () -> new Aload2Instruction());
		list.add(0x2D, "aload_3", Aload3Instruction.class, () -> new Aload3Instruction());
		list.add(0x2E, "iaload", IaloadInstruction.class, () -> new IaloadInstruction());
		list.add(0x2F, "laload", LaloadInstruction.class, () -> new LaloadInstruction());
		list.add(0x30, "faload", FaloadInstruction.class, () -> new FaloadInstruction());
		list.add(0x31, "daload", DaloadInstruction.class, () -> new DaloadInstruction());
		list.add(0x32, "aaload", AaloadInstruction.class, () -> new AaloadInstruction());
		list.add(0x33, "baload", BaloadInstruction.class, () -> new BaloadInstruction());
		list.add(0x34, "caload", CaloadInstruction.class, () -> new CaloadInstruction());
		list.add(0x35, "saload", SaloadInstruction.class, () -> new SaloadInstruction());

		// Stores
		list.add(0x36, "istore", IstoreInstruction.class, () -> new IstoreInstruction());
		list.add(0x37, "lstore", LstoreInstruction.class, () -> new LstoreInstruction());
		list.add(0x38, "fstore", FstoreInstruction.class, () -> new FstoreInstruction());
		list.add(0x39, "dstore", DstoreInstruction.class, () -> new DstoreInstruction());
		list.add(0x3A, "dstore", AstoreInstruction.class, () -> new AstoreInstruction());
		list.add(0x3B, "istore_0", Istore0Instruction.class, () -> new Istore0Instruction());
		list.add(0x3C, "istore_1", Istore1Instruction.class, () -> new Istore1Instruction());
		list.add(0x3D, "istore_2", Istore2Instruction.class, () -> new Istore2Instruction());
		list.add(0x3E, "istore_3", Istore3Instruction.class, () -> new Istore3Instruction());
		list.add(0x3F, "lstore_0", Lstore0Instruction.class, () -> new Lstore0Instruction());
		list.add(0x40, "lstore_1", Lstore1Instruction.class, () -> new Lstore1Instruction());
		list.add(0x41, "lstore_2", Lstore2Instruction.class, () -> new Lstore2Instruction());
		list.add(0x42, "lstore_3", Lstore3Instruction.class, () -> new Lstore3Instruction());
		list.add(0x43, "fstore_0", Fstore0Instruction.class, () -> new Fstore0Instruction());
		list.add(0x44, "fstore_1", Fstore1Instruction.class, () -> new Fstore1Instruction());
		list.add(0x45, "fstore_2", Fstore2Instruction.class, () -> new Fstore2Instruction());
		list.add(0x46, "fstore_3", Fstore3Instruction.class, () -> new Fstore3Instruction());
		list.add(0x47, "dstore_0", Dstore0Instruction.class, () -> new Dstore0Instruction());
		list.add(0x48, "dstore_1", Dstore1Instruction.class, () -> new Dstore1Instruction());
		list.add(0x49, "dstore_2", Dstore2Instruction.class, () -> new Dstore2Instruction());
		list.add(0x4A, "dstore_3", Dstore3Instruction.class, () -> new Dstore3Instruction());
		list.add(0x4B, "astore_0", Astore0Instruction.class, () -> new Astore0Instruction());
		list.add(0x4C, "astore_1", Astore1Instruction.class, () -> new Astore1Instruction());
		list.add(0x4D, "astore_2", Astore2Instruction.class, () -> new Astore2Instruction());
		list.add(0x4E, "astore_3", Astore3Instruction.class, () -> new Astore3Instruction());
		list.add(0x4F, "iastore", IastoreInstruction.class, () -> new IastoreInstruction());
		list.add(0x50, "lastore", LastoreInstruction.class, () -> new LastoreInstruction());
		list.add(0x51, "fastore", FastoreInstruction.class, () -> new FastoreInstruction());
		list.add(0x52, "dastore", DastoreInstruction.class, () -> new DastoreInstruction());
		list.add(0x53, "aastore", AastoreInstruction.class, () -> new AastoreInstruction());
		list.add(0x54, "bastore", BastoreInstruction.class, () -> new BastoreInstruction());
		list.add(0x55, "castore", CastoreInstruction.class, () -> new CastoreInstruction());
		list.add(0x56, "sastore", SastoreInstruction.class, () -> new SastoreInstruction());

		// Stack
		list.add(0x57, "pop", PopInstruction.class, () -> new PopInstruction());
		list.add(0x58, "pop2", Pop2Instruction.class, () -> new Pop2Instruction());
		list.add(0x59, "dup", DupInstruction.class, () -> new DupInstruction());
		list.add(0x5A, "dup_x1", DupX1Instruction.class, () -> new DupX1Instruction());
		list.add(0x5B, "dup_x2", DupX2Instruction.class, () -> new DupX2Instruction());
		list.add(0x5C, "dup2", Dup2Instruction.class, () -> new Dup2Instruction());
		list.add(0x5D, "dup2_x1", Dup2X1Instruction.class, () -> new Dup2X1Instruction());
		list.add(0x5E, "dup2_x2", Dup2X2Instruction.class, () -> new Dup2X2Instruction());
		list.add(0x5F, "swap", SwapInstruction.class, () -> new SwapInstruction());

		// Math
		list.add(0x60, "iadd", IaddInstruction.class, () -> new IaddInstruction());
		list.add(0x61, "ladd", LaddInstruction.class, () -> new LaddInstruction());
		list.add(0x62, "fadd", FaddInstruction.class, () -> new FaddInstruction());
		list.add(0x63, "dadd", DaddInstruction.class, () -> new DaddInstruction());
		list.add(0x64, "isub", IsubInstruction.class, () -> new IsubInstruction());
		list.add(0x65, "lsub", LsubInstruction.class, () -> new LsubInstruction());
		list.add(0x66, "fsub", FsubInstruction.class, () -> new FsubInstruction());
		list.add(0x67, "dsub", DsubInstruction.class, () -> new DsubInstruction());
		list.add(0x68, "imul", ImulInstruction.class, () -> new ImulInstruction());
		list.add(0x69, "lmul", LmulInstruction.class, () -> new LmulInstruction());
		list.add(0x6A, "fmul", FmulInstruction.class, () -> new FmulInstruction());
		list.add(0x6B, "dmul", DmulInstruction.class, () -> new DmulInstruction());
		list.add(0x6C, "idiv", IdivInstruction.class, () -> new IdivInstruction());
		list.add(0x6D, "ldiv", LdivInstruction.class, () -> new LdivInstruction());
		list.add(0x6E, "fdiv", FdivInstruction.class, () -> new FdivInstruction());
		list.add(0x6F, "ddiv", DdivInstruction.class, () -> new DdivInstruction());
		list.add(0x70, "irem", IremInstruction.class, () -> new IremInstruction());
		list.add(0x71, "lrem", LremInstruction.class, () -> new LremInstruction());
		list.add(0x72, "frem", FremInstruction.class, () -> new FremInstruction());
		list.add(0x73, "drem", DremInstruction.class, () -> new DremInstruction());
		list.add(0x74, "ineg", InegInstruction.class, () -> new InegInstruction());
		list.add(0x75, "lneg", LnegInstruction.class, () -> new LnegInstruction());
		list.add(0x76, "fneg", FnegInstruction.class, () -> new FnegInstruction());
		list.add(0x77, "dneg", DnegInstruction.class, () -> new DnegInstruction());
		list.add(0x78, "ishl", IshlInstruction.class, () -> new IshlInstruction());
		list.add(0x79, "lshl", LshlInstruction.class, () -> new LshlInstruction());
		list.add(0x7A, "ishr", IshrInstruction.class, () -> new IshrInstruction());
		list.add(0x7B, "lshr", LshrInstruction.class, () -> new LshrInstruction());
		list.add(0x7C, "iushr", IushrInstruction.class, () -> new IushrInstruction());
		list.add(0x7D, "lushr", LushrInstruction.class, () -> new LushrInstruction());
		list.add(0x7E, "iand", IandInstruction.class, () -> new IandInstruction());
		list.add(0x7F, "land", LandInstruction.class, () -> new LandInstruction());
		list.add(0x80, "ior", IorInstruction.class, () -> new IorInstruction());
		list.add(0x81, "lor", LorInstruction.class, () -> new LorInstruction());
		list.add(0x82, "ixor", IxorInstruction.class, () -> new IxorInstruction());
		list.add(0x83, "lxor", LxorInstruction.class, () -> new LxorInstruction());
		list.add(0x84, "iinc", IincInstruction.class, () -> new IincInstruction());

		// Conversions
		list.add(0x85, "i2l", I2lInstruction.class, () -> new I2lInstruction());
		list.add(0x86, "i2f", I2fInstruction.class, () -> new I2fInstruction());
		list.add(0x87, "i2d", I2dInstruction.class, () -> new I2dInstruction());
		list.add(0x88, "l2i", L2fInstruction.class, () -> new L2iInstruction());
		list.add(0x89, "l2f", L2fInstruction.class, () -> new L2fInstruction());
		list.add(0x8A, "l2d", L2dInstruction.class, () -> new L2dInstruction());
		list.add(0x8B, "f2i", F2iInstruction.class, () -> new F2iInstruction());
		list.add(0x8C, "f2l", F2lInstruction.class, () -> new F2lInstruction());
		list.add(0x8D, "f2d", F2dInstruction.class, () -> new F2dInstruction());
		list.add(0x8E, "d2i", D2iInstruction.class, () -> new D2iInstruction());
		list.add(0x8F, "d2l", D2lInstruction.class, () -> new D2lInstruction());
		list.add(0x90, "d2f", D2fInstruction.class, () -> new D2fInstruction());
		list.add(0x91, "i2b", I2bInstruction.class, () -> new I2bInstruction());
		list.add(0x92, "i2c", I2cInstruction.class, () -> new I2cInstruction());
		list.add(0x93, "i2s", I2sInstruction.class, () -> new I2sInstruction());

		// Comparisions
		list.add(0x94, "lcmp", LcmpInstruction.class, () -> new LcmpInstruction());
		list.add(0x95, "fcmpl", FcmplInstruction.class, () -> new FcmplInstruction());
		list.add(0x96, "fcmpg", FcmpgInstruction.class, () -> new FcmpgInstruction());
		list.add(0x97, "dcmpl", DcmplInstruction.class, () -> new DcmplInstruction());
		list.add(0x98, "dcmpg", DcmpgInstruction.class, () -> new DcmpgInstruction());
		list.add(0x99, "ifeq", IfeqInstruction.class, () -> new IfeqInstruction());
		list.add(0x9A, "ifne", IfneInstruction.class, () -> new IfneInstruction());
		list.add(0x9B, "iflt", IfltInstruction.class, () -> new IfltInstruction());
		list.add(0x9C, "ifge", IfgeInstruction.class, () -> new IfgeInstruction());
		list.add(0x9D, "ifgt", IfgtInstruction.class, () -> new IfgtInstruction());
		list.add(0x9E, "ifle", IfleInstruction.class, () -> new IfleInstruction());
		list.add(0x9F, "if_icmpeq", IfIcmpeqInstruction.class, () -> new IfIcmpeqInstruction());
		list.add(0xA0, "if_icmpne", IfIcmpneInstruction.class, () -> new IfIcmpneInstruction());
		list.add(0xA1, "if_icmplt", IfIcmpltInstruction.class, () -> new IfIcmpltInstruction());
		list.add(0xA2, "if_icmpge", IfIcmpgeInstruction.class, () -> new IfIcmpgeInstruction());
		list.add(0xA3, "if_icmpgt", IfIcmpgtInstruction.class, () -> new IfIcmpgtInstruction());
		list.add(0xA4, "if_icmple", IfIcmpleInstruction.class, () -> new IfIcmpleInstruction());
		list.add(0xA5, "if_acmpeq", IfAcmpeqInstruction.class, () -> new IfAcmpeqInstruction());
		list.add(0xA6, "if_acmpne", IfAcmpneInstruction.class, () -> new IfAcmpneInstruction());

		// Control
		list.add(0xA7, "goto", GotoInstruction.class, () -> new GotoInstruction());
		list.add(0xA8, "jsr", JsrInstruction.class, () -> new JsrInstruction());
		list.add(0xA9, "ret", RetInstruction.class, () -> new RetInstruction());
		list.add(0xAA, "tableswitch", TableswitchInstruction.class, () -> new TableswitchInstruction());
		list.add(0xAB, "lookupswitch", LookupswitchInstruction.class, () -> new LookupswitchInstruction());
		list.add(0xAC, "ireturn", IreturnInstruction.class, () -> new IreturnInstruction());
		list.add(0xAD, "lreturn", LreturnInstruction.class, () -> new LreturnInstruction());
		list.add(0xAE, "freturn", FreturnInstruction.class, () -> new FreturnInstruction());
		list.add(0xAF, "dreturn", DreturnInstruction.class, () -> new DreturnInstruction());
		list.add(0xB0, "areturn", AreturnInstruction.class, () -> new AreturnInstruction());
		list.add(0xB1, "return", ReturnInstruction.class, () -> new ReturnInstruction());
		
		// References
		list.add(0xB2, "getstatic", GetstaticInstruction.class, () -> new GetstaticInstruction());
		list.add(0xB3, "putstatic", PutstaticInstruction.class, () -> new PutstaticInstruction());
		list.add(0xB4, "getfield", GetfieldInstruction.class, () -> new GetfieldInstruction());
		list.add(0xB5, "putfield", PutfieldInstruction.class, () -> new PutfieldInstruction());
		list.add(0xB6, "invokevirtual", InvokevirtualInstruction.class, () -> new InvokevirtualInstruction());
		list.add(0xB7, "invokespecial", InvokespecialInstruction.class, () -> new InvokespecialInstruction());
		list.add(0xB8, "invokestatic", InvokestaticInstruction.class, () -> new InvokestaticInstruction());
		list.add(0xB9, "invokeinterface", InvokeinterfaceInstruction.class, () -> new InvokeinterfaceInstruction());
		list.add(0xBA, "invokedynamic", InvokedynamicInstruction.class, () -> new InvokedynamicInstruction());
		list.add(0xBB, "new", NewInstruction.class, () -> new NewInstruction());
		list.add(0xBC, "newarray", NewarrayInstruction.class, () -> new NewarrayInstruction());
		list.add(0xBD, "anewarray", AnewarrayInstruction.class, () -> new AnewarrayInstruction());
		list.add(0xBE, "arraylength", ArraylengthInstruction.class, () -> new ArraylengthInstruction());
		list.add(0xBF, "athrow", AthrowInstruction.class, () -> new AthrowInstruction());
		list.add(0xC0, "checkcast", CheckcastInstruction.class, () -> new CheckcastInstruction());
		list.add(0xC1, "instanceof", InstanceofInstruction.class, () -> new InstanceofInstruction());
		list.add(0xC2, "monitorenter", MonitorenterInstruction.class, () -> new MonitorenterInstruction());
		list.add(0xC3, "monitorexit", MonitorexitInstruction.class, () -> new MonitorexitInstruction());

		// Extended
		list.add(0xC4, "wide", WideInstruction.class, () -> new WideInstruction());
		list.add(0xC5, "multianewarray", MultianewarrayInstruction.class, () -> new MultianewarrayInstruction());
		list.add(0xC6, "ifnull", IfnullInstruction.class, () -> new IfnullInstruction());
		list.add(0xC7, "ifnonnull", IfnonnullInstruction.class, () -> new IfnonnullInstruction());
		list.add(0xC8, "goto_w", GotoWInstruction.class, () -> new GotoWInstruction());
		list.add(0xC9, "jsr_w", JsrWInstruction.class, () -> new JsrWInstruction());

		// Reserved
		list.add(0xCA, "breakpoint", BreakpointInstruction.class, () -> new BreakpointInstruction());
		list.add(0xFE, "impdep1", Impdep1Instruction.class, () -> new Impdep1Instruction());
		list.add(0xFF, "impdep2", Impdep2Instruction.class, () -> new Impdep2Instruction());

		opcodeFromMnemonic = new HashMap<>(list.size());
		mnemonicFromOpcode = new HashMap<>(list.size());
		supplierFromOpcode = new HashMap<>(list.size());
		classFromOpcode = new HashMap<>(list.size());
		opcodeFromClass = new HashMap<>(list.size());

		list.forEach(element -> {
			mnemonicFromOpcode.put(element.getOpcode(), element.getMnemonic());
			opcodeFromMnemonic.put(element.getMnemonic(), element.getOpcode());
			supplierFromOpcode.put(element.getOpcode(), element.getInstructionSupplier());
			classFromOpcode.put(element.getOpcode(), element.getInstructionClass());
			opcodeFromClass.put(element.getInstructionClass(), element.getOpcode());
		});
	}

	@SneakyThrows
	public <T extends Instruction<T>> T getInstruction(MultiEndianInputStream input) {
		var opcode = input.readUnsignedByte();

		var instruction = supplierFromOpcode.get(opcode).get();

		input.unreadUnsignedByte(opcode);

		return instruction != null ? (T) instruction.read(input) : null;
	}

	public <T extends Instruction<T>> Class<T> getInstructionClass(short opcode) {
		return (Class<T>) classFromOpcode.get(opcode);
	}

	public String getMnemonic(short opcode) {
		return mnemonicFromOpcode.get(opcode);
	}

	public short getOpcode(String mnemonic) {
		return opcodeFromMnemonic.get(mnemonic);
	}

	public short getOpcode(Class<? extends Instruction<?>> c) {
		return opcodeFromClass.get(c);
	}

	@Getter
	@AllArgsConstructor
	private class InstructionMapping {

		private short opcode;
		private String mnemonic;
		private Class<? extends Instruction<?>> instructionClass;
		private Supplier<Instruction<?>> instructionSupplier;

	}

}
