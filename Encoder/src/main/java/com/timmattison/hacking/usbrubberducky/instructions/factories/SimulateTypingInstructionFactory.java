package com.timmattison.hacking.usbrubberducky.instructions.factories;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.SimulateTypingInstruction;

/**
 * Created by timmattison on 12/16/13.
 */
public interface SimulateTypingInstructionFactory {
    public static final String MINIMUM_DELAY = "minimumDelay";
    public static final String MAXIMUM_DELAY = "maximumDelay";
    public static final String INPUT = "input";

    SimulateTypingInstruction create(@Assisted(MINIMUM_DELAY) int minimumDelay,
                                     @Assisted(MAXIMUM_DELAY) int maximumDelay,
                                     @Assisted(INPUT) String input);
}
