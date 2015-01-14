package com.timmattison.hacking.usbrubberducky.instructions.interfaces;

import com.timmattison.hacking.usbrubberducky.instructions.Instruction;

public interface SimulateTypingInstruction extends Instruction {
    /**
     * Get the minimum delay in milliseconds
     *
     * @return
     */
    public int getMinimumDelay();

    /**
     * Get the maximum delay in milliseconds
     *
     * @return
     */
    public int getMaximumDelay();

    /**
     * Get the string to type
     *
     * @return
     */
    public String getInput();
}
