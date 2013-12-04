package com.timmattison.hacking.usbrubberducky.instructions.lists;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.RepeatInstruction;

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

        for (Instruction instruction : instructionList) {
            if (instruction instanceof RepeatInstruction) {
                RepeatInstruction repeatInstruction = (RepeatInstruction) instruction;
                for (int loop = 0; loop < repeatInstruction.getRepeatCount(); loop++) {
                    baos.write(lastInstruction.getEncodedInstruction());
                }
            }

            // Track the last instruction so we can repeat it
            lastInstruction = instruction;
        }

        return baos.toByteArray();
    }
}
