package com.timmattison.hacking.usbrubberducky.instructions.lists;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicInstructionList implements InstructionList {
    protected List<Instruction> instructionList = new ArrayList<Instruction>();

    @Override
    public void addInstruction(Instruction instruction) {
        instructionList.add(instruction);
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Instruction lastInstruction = null;

        // Loop through all of the instructions
        for (Instruction instruction : instructionList) {
            // Is this instruction a repeat instruction?
            if (instruction instanceof RepeatInstruction) {
                // Yes, get the repeat instruction
                RepeatInstruction repeatInstruction = (RepeatInstruction) instruction;

                // Not supporting multiple instructions yet
                if (repeatInstruction.getInstructionCount() != 1) {
                    throw new UnsupportedOperationException("Instruction count > 1, not supported yet");
                }

                // Is the last instruction NULL?
                if (lastInstruction == null) {
                    throw new UnsupportedOperationException("Either REPEAT was used as the first instruction or REPEAT was used immediately after a REPEAT.  This behavior is not supported.");
                }

                // Loop as many times as required
                for (int loop = 0; loop < repeatInstruction.getRepeatCount(); loop++) {
                    baos.write(lastInstruction.getEncodedInstruction());
                }
            } else {
                // No, just encode the instruction
                baos.write(instruction.getEncodedInstruction());
            }

            // Track the last instruction so we can repeat it
            lastInstruction = instruction;
        }

        return baos.toByteArray();
    }

    public List<Instruction> getInstructions() {
        return instructionList;
    }
}
