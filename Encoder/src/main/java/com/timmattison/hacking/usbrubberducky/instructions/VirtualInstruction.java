package com.timmattison.hacking.usbrubberducky.instructions;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;

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
        throw new UnsupportedOperationException("This is a virtual instruction, it must not be processed.");
    }
}
