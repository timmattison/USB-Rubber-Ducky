package com.timmattison.hacking.usbrubberducky.instructions.factories;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;

/**
 * Created by timmattison on 12/16/13.
 */
public interface DelayInstructionFactory {
    public static final String DELAY_IN_MILLISECONDS = "delayInMilliseconds";

    DelayInstruction create(@Assisted(DELAY_IN_MILLISECONDS) int delayInMilliseconds);
}
