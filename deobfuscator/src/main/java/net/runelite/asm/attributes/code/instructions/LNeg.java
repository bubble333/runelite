package net.runelite.asm.attributes.code.instructions;

import net.runelite.asm.attributes.code.Instruction;
import net.runelite.asm.attributes.code.InstructionType;
import net.runelite.asm.attributes.code.Instructions;
import net.runelite.asm.execution.Frame;
import net.runelite.asm.execution.InstructionContext;
import net.runelite.asm.execution.Stack;
import net.runelite.asm.execution.StackContext;
import net.runelite.asm.execution.Value;

public class LNeg extends Instruction
{
	public LNeg(Instructions instructions, InstructionType type, int pc)
	{
		super(instructions, type, pc);
	}

	@Override
	public InstructionContext execute(Frame frame)
	{
		InstructionContext ins = new InstructionContext(this, frame);
		Stack stack = frame.getStack();
		
		StackContext value = stack.pop();
		ins.pop(value);
		
		Value result = Value.NULL;
		if (!value.getValue().isNull())
		{
			long l = (long) value.getValue().getValue();
			
			result = new Value(-l);
		}
		
		StackContext ctx = new StackContext(ins, long.class, result);
		stack.push(ctx);
		
		ins.push(ctx);
		
		return ins;
	}
}