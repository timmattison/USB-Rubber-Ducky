package com.timmattison.hacking.usbrubberducky.instructions.lists;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/27/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface InstructionList {
    public void addInstruction(Instruction instruction);

    public byte[] getBytes() throws IOException;
}
