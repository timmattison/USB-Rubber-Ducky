package com.timmattison.hacking.usbrubberducky.instructions;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.factories.SimulateTypingInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.SimulateTypingInstruction;

public class BasicSimulateTypingInstruction extends VirtualInstruction implements SimulateTypingInstruction {
    private final int minimumDelay;
    private final int maximumDelay;
    private final String input;

    @Inject
    public BasicSimulateTypingInstruction(@Assisted(SimulateTypingInstructionFactory.MINIMUM_DELAY) int minimumDelay,
                                          @Assisted(SimulateTypingInstructionFactory.MAXIMUM_DELAY) int maximumDelay,
                                          @Assisted(SimulateTypingInstructionFactory.INPUT) String input) {
        this.minimumDelay = minimumDelay;
        this.maximumDelay = maximumDelay;
        this.input = input;
    }

    @Override
    public int getMinimumDelay() {
        return minimumDelay;
    }

    @Override
    public int getMaximumDelay() {
        return maximumDelay;
    }

    @Override
    public String getInput() {
        return input;
    }
}
