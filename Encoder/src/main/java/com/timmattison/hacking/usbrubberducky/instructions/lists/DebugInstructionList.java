package com.timmattison.hacking.usbrubberducky.instructions.lists;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
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
public class DebugInstructionList implements InstructionList {
    private final Set<InstructionListProcessor> instructionListProcessors;
    private final int debugOffset;

    protected List<Instruction> instructionList = new ArrayList<Instruction>();

    public DebugInstructionList(int debugOffset, Set<InstructionListProcessor> instructionListProcessors) {
        this.debugOffset = debugOffset;
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
            while (true) {
                // Run each instruction list processor until it makes no more changes
                List<Instruction> tempInstructionList = instructionListProcessor.process(finalInstructionList);

                // Is this the same as the final instruction list?
                if (tempInstructionList.equals(finalInstructionList)) {
                    // Yes, break from the loop
                    break;
                }

                // The lists were different.  Update our final list and try again
                finalInstructionList = tempInstructionList;
            }
        }

        // Loop through all of the instructions
        for (Instruction instruction : finalInstructionList) {
            byte[] encodedInstruction = instruction.getEncodedInstruction();

            System.out.println(instruction);
            if(baos.size() >= debugOffset) {
                System.out.println("STOP HERE");
            }

            baos.write(encodedInstruction);
        }

        return baos.toByteArray();
    }

    public List<Instruction> getInstructions() {
        return instructionList;
    }
}
