package com.timmattison.hacking.usbrubberducky.instructions;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/3/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class VirtualInstruction implements Instruction {
    @Override
    public final byte[] getEncodedInstruction() {
        /**
         * If someone calls this function it means that the virtual instruction should have been translated by a list
         * processor but wasn't.  In this case we need to throw an exception so that the encoding process fails.
         */

        throw new UnsupportedOperationException("This is a virtual instruction, it must not be processed.");
    }
}
