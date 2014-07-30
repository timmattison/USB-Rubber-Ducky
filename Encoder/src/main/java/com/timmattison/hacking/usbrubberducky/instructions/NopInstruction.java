package com.timmattison.hacking.usbrubberducky.instructions;

/**
 * Created by timmattison on 12/11/13.
 */
public class NopInstruction implements Instruction {
    @Override
    public byte[] getEncodedInstruction() {
        // Return an empty array to indicate that this instruction does nothing
        return new byte[0];
    }
}
