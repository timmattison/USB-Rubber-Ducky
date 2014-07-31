package com.timmattison.hacking.usbrubberducky.exceptions;

import com.timmattison.hacking.usbrubberducky.instructions.VirtualInstruction;

/**
 * Created by timmattison on 7/31/14.
 */
public class VirtualInstructionEncodedException extends Throwable {
    private final VirtualInstruction virtualInstruction;

    public VirtualInstructionEncodedException(VirtualInstruction virtualInstruction) {
        this.virtualInstruction = virtualInstruction;
    }

    public VirtualInstruction getVirtualInstruction() {
        return virtualInstruction;
    }
}
