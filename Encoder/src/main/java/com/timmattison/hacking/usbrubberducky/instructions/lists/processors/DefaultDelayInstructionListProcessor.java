package com.timmattison.hacking.usbrubberducky.instructions.lists.processors;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.DefaultDelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.DelayInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timmattison on 7/29/14.
 */
public class DefaultDelayInstructionListProcessor implements InstructionListProcessor {
    private final DelayInstructionFactory delayInstructionFactory;
    private int defaultDelay;

    @Inject
    public DefaultDelayInstructionListProcessor(DelayInstructionFactory delayInstructionFactory) {
        this.delayInstructionFactory = delayInstructionFactory;
    }

    @Override
    public List<Instruction> process(List<Instruction> instructionList) {
        // Find and set the default delay
        setDefaultDelay(instructionList);

        // Was there a default delay value?
        if (defaultDelay == -1) {
            // No, just return the instruction list
            return instructionList;
        }

        List<Instruction> outputInstructionList = new ArrayList<Instruction>();

        for (Instruction instruction : instructionList) {
            // What kind of instruction is this?
            if (instruction instanceof DelayInstruction) {
                // This is a delay instruction, include it without an additional delay
                outputInstructionList.add(instruction);
            } else if (instruction instanceof DefaultDelayInstruction) {
                // This is the default delay instruction, do not include it
            } else {
                // This is another instruction, add it with a trailing delay
                outputInstructionList.add(instruction);
                outputInstructionList.add(delayInstructionFactory.create(defaultDelay));
            }
        }

        return outputInstructionList;
    }

    private void setDefaultDelay(List<Instruction> instructionList) {
        defaultDelay = -1;

        for (Instruction instruction : instructionList) {
            // Is this a default delay instruction?
            if (instruction instanceof DefaultDelayInstruction) {
                // Did we already set the default delay?
                if (defaultDelay != -1) {
                    // Yes, we can only set the default delay once per script
                    throw new UnsupportedOperationException("Only one default delay instruction is allowed per script");
                }

                // Default delay has not been set yet.  Set it now.
                defaultDelay = ((DefaultDelayInstruction) instruction).getDelayValue();
            }
        }
    }
}
