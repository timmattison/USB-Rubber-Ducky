package com.timmattison.hacking.usbrubberducky.instructions.lists.processors;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.NopInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timmattison on 7/29/14.
 */
public class NopInstructionListProcessor implements InstructionListProcessor {
    @Override
    public List<Instruction> process(List<Instruction> instructionList) {
        List<Instruction> outputInstructionList = new ArrayList<Instruction>();

        for (Instruction instruction : instructionList) {
            // Is this a NOP instruction?
            if (!(instruction instanceof NopInstruction)) {
                // No, use it
                outputInstructionList.add(instruction);
            }
        }

        return outputInstructionList;
    }
}
