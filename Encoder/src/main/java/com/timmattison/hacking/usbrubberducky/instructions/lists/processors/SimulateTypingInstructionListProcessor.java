package com.timmattison.hacking.usbrubberducky.instructions.lists.processors;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.StringInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.DelayInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.StringInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.SimulateTypingInstruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Handles REPEAT instructions
 * <p/>
 * Created by timmattison on 7/29/14.
 */
public class SimulateTypingInstructionListProcessor implements InstructionListProcessor {
    private final DelayInstructionFactory delayInstructionFactory;
    private final StringInstructionFactory stringInstructionFactory;

    @Inject
    public SimulateTypingInstructionListProcessor(DelayInstructionFactory delayInstructionFactory, StringInstructionFactory stringInstructionFactory) {
        this.delayInstructionFactory = delayInstructionFactory;
        this.stringInstructionFactory = stringInstructionFactory;
    }

    @Override
    public List<Instruction> process(List<Instruction> instructionList) {
        // Create the output instruction list
        List<Instruction> outputInstructionList = new ArrayList<Instruction>();

        int counter = 0;

        for (Instruction instruction : instructionList) {
            // Is this a simulate typing instruction?
            if (instruction instanceof SimulateTypingInstruction) {
                // Yes, get the simulate typing instruction
                SimulateTypingInstruction simulateTypingInstruction = (SimulateTypingInstruction) instruction;

                // Get the minimum delay, maximum delay, and input
                int minimumDelay = simulateTypingInstruction.getMinimumDelay();
                int maximumDelay = simulateTypingInstruction.getMaximumDelay();
                String input = simulateTypingInstruction.getInput();

                // Create a random number generator that gives repeatable results
                Random random = new Random(counter);

                // Loop through all of the characters
                for (char currentCharacter : input.toCharArray()) {
                    int delay = random.nextInt(maximumDelay - minimumDelay) + minimumDelay;

                    DelayInstruction delayInstruction = delayInstructionFactory.create(delay);
                    StringInstruction stringInstruction = stringInstructionFactory.create(String.valueOf(currentCharacter));

                    outputInstructionList.add(delayInstruction);
                    outputInstructionList.add(stringInstruction);
                }
            } else {
                // No, just add the instruction
                outputInstructionList.add(instruction);
            }

            counter++;
        }

        // Return the output instruction list
        return outputInstructionList;
    }
}
