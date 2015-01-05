package com.timmattison.hacking.usbrubberducky.instructions.factories;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;

/**
 * Created by timmattison on 12/16/13.
 */
public interface RepeatInstructionFactory {
    public static final String REPEAT_COUNT = "repeatCount";
    public static final String INSTRUCTION_COUNT = "instructionCount";

    RepeatInstruction create(@Assisted(REPEAT_COUNT) int repeatCount, @Assisted(INSTRUCTION_COUNT) int instructionCount);
}
