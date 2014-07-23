package com.timmattison.hacking.usbrubberducky.instructions.interfaces;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/3/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RepeatInstruction extends Instruction {
    public int getRepeatCount();

    public int getInstructionCount();
}
