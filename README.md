<p align="center">
  <img src="chamomile.png" alt="project logo" />
</p>
<br/>

<h3 align="center">Chamomile is a Java Virtual Machine class file assembler and disassembler.</h3>
<br/>

<h3>Installation</h3>

<h4>Maven</h4>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.wdestroier</groupId>
        <artifactId>chamomile</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

<h4>Gradle</h4>

```kotlin
repositories {
  maven("https://jitpack.io")
}

dependencies {
  implementation("com.github.wdestroier:chamomile:1.0.0")
}
```

<br/>
<h3>Demonstration</h3>

<h4>Assemble a list of instructions</h4>

```java
var assembler = new SinglePassAssembler();

var bytecode = assembler.assemble(List.of(
		new Iconst1Instruction(),
		new I2lInstruction(),
		new AconstNullInstruction(),
		new PopInstruction(),
		new Fconst2Instruction(),
		new F2lInstruction(),
		new LaddInstruction(),
		new InvokestaticInstruction(InstructionFactory.instance.getOpcode(
	InvokestaticInstruction.class), 12)
		));

System.out.println(Arrays.toString(bytecode));
```

<h4>Output</h4>

```txt
[4, 133, 1, 87, 13, 140, 97, 184, 0, 12]
```

<br/>
<h4>Disassemble an array of "unsigned bytes"</h4>

```java
var disassembler = new LinearSweepDisassembler();

var bytecode = new short[] { 4, 133, 1, 87, 13, 140, 97, 184, 0, 12 };

var instructions = disassembler.disassemble(bytecode);

instructions.stream()
	.map(instruction -> InstructionFactory.instance.getMnemonic(instruction.getOpcode()))
	.forEach(System.out::println);
```

<h4>Output</h4>

```txt
iconst_1
i2l
aconst_null
pop
fconst_2
f2l
ladd
invokestatic
```

<br/>

More examples can be found [here](/examples)

<br/>
