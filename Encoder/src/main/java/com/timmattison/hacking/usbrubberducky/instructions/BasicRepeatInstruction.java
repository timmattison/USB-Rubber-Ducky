package com.timmattison.hacking.usbrubberducky.instructions;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.factories.RepeatInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/3/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class BasicRepeatInstruction extends VirtualInstruction implements RepeatInstruction {
    private final int repeatCount;
    private final int instructionCount;

    @Inject
    public BasicRepeatInstruction(@Assisted(RepeatInstructionFactory.REPEAT_COUNT) int repeatCount,
                                  @Assisted(RepeatInstructionFactory.INSTRUCTION_COUNT) int instructionCount) {
        this.repeatCount = repeatCount;
        this.instructionCount = instructionCount;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public int getInstructionCount() {
        return instructionCount;
    }
}
