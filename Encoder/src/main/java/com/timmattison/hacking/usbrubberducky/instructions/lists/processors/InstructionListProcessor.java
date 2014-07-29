package com.timmattison.hacking.usbrubberducky.instructions.lists.processors;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionList;

import java.util.List;

/**
 * Created by timmattison on 7/29/14.
 */
public interface InstructionListProcessor {
    List<Instruction> process(List<Instruction> instructionList);
}
