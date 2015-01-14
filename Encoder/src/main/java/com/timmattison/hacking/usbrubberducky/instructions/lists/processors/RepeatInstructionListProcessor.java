package com.timmattison.hacking.usbrubberducky.instructions.lists.processors;

import com.timmattison.hacking.usbrubberducky.exceptions.NotEnoughInstructionsToRepeatException;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles REPEAT instructions
 * <p/>
 * Created by timmattison on 7/29/14.
 */
public class RepeatInstructionListProcessor implements InstructionListProcessor {
    @Override
    public List<Instruction> process(List<Instruction> instructionList) throws NotEnoughInstructionsToRepeatException {
        // Create the output instruction list
        List<Instruction> outputInstructionList = new ArrayList<Instruction>();

        for (Instruction instruction : instructionList) {
            // Is this a repeat instruction?
            if (instruction instanceof RepeatInstruction) {
                // Yes, get the repeat instruction
                RepeatInstruction repeatInstruction = (RepeatInstruction) instruction;

                // Get the instruction count and the repeat count
                int instructionCount = repeatInstruction.getInstructionCount();
                int repeatCount = repeatInstruction.getRepeatCount();

                // Are there enough instructions to do this repeat?
                if (outputInstructionList.size() < instructionCount) {
                    // No, throw an exception
                    throw new NotEnoughInstructionsToRepeatException(outputInstructionList.size(), instructionCount);
                }

                // Create a sublist that is a copy of all of the instructions we want to repeat
                List<Instruction> instructionSublist = new ArrayList<Instruction>(outputInstructionList.subList(outputInstructionList.size() - instructionCount, outputInstructionList.size()));

                // Repeat the sublist the requested number of times
                for (int loop = 0; loop < repeatCount; loop++) {
                    outputInstructionList.addAll(instructionSublist);
                }
            } else {
                // No, just add the instruction
                outputInstructionList.add(instruction);
            }
        }

        // Return the output instruction list
        return outputInstructionList;
    }
}
