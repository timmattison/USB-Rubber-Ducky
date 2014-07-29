package com.timmattison.hacking.usbrubberducky.instructions.lists;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.InstructionListProcessor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicInstructionList implements InstructionList {
    private final Set<InstructionListProcessor> instructionListProcessors;

    protected List<Instruction> instructionList = new ArrayList<Instruction>();

    public BasicInstructionList(Set<InstructionListProcessor> instructionListProcessors) {
        this.instructionListProcessors = instructionListProcessors;
    }

    @Override
    public void addInstruction(Instruction instruction) {
        instructionList.add(instruction);
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        List<Instruction> finalInstructionList = new ArrayList<Instruction>();

        finalInstructionList.addAll(instructionList);

        for (InstructionListProcessor instructionListProcessor : instructionListProcessors) {
            List<Instruction> tempInstructionList;

            do {
                // Run each instruction list processor until it makes no more changes
                tempInstructionList = instructionListProcessor.process(finalInstructionList);
            } while (!tempInstructionList.equals(finalInstructionList));
        }

        // Loop through all of the instructions
        for (Instruction instruction : finalInstructionList) {
            // No, just encode the instruction
            baos.write(instruction.getEncodedInstruction());
        }

        return baos.toByteArray();
    }

    public List<Instruction> getInstructions() {
        return instructionList;
    }
}
