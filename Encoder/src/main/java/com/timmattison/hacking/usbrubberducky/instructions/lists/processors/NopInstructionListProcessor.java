package com.timmattison.hacking.usbrubberducky.instructions.lists.processors;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
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
            // Is this a repeat instruction?
            if (instruction instanceof RepeatInstruction) {
                // Yes, get the repeat instruction
                RepeatInstruction repeatInstruction = (RepeatInstruction) instruction;

                int instructionCount = repeatInstruction.getInstructionCount();
                int repeatCount = repeatInstruction.getRepeatCount();

                // Are there enough instructions to do this repeat?
                if (outputInstructionList.size() < instructionCount) {
                    // No, throw an exception
                    throw new UnsupportedOperationException("Trying to repeat " + instructionCount + " instruction(s) but only " + outputInstructionList.size() + " instruction(s) are available");
                }

                // Create a sublist that holds all of the instructions we want ot repeat
                List<Instruction> instructionSublist = new ArrayList<Instruction>();

                instructionSublist.addAll(outputInstructionList.subList(outputInstructionList.size() - instructionCount, outputInstructionList.size()));

                // Repeat the sublist the expected number of times
                for (int loop = 0; loop < repeatInstruction.getRepeatCount(); loop++) {
                    outputInstructionList.addAll(instructionSublist);
                }
            } else {
                // No, just encode the instruction
                outputInstructionList.add(instruction);
            }
        }

        return outputInstructionList;
    }
}