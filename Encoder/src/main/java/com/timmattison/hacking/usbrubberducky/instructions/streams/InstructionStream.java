package com.timmattison.hacking.usbrubberducky.instructions.streams;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/27/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface InstructionStream {
    public List<Instruction> getInstructionStream(String input);
}
