package com.timmattison.hacking.usbrubberducky.exceptions;

/**
 * Created by timmattison on 7/31/14.
 */
public class NotEnoughInstructionsToRepeatException extends Throwable {
    private final int instructionsAvailable;
    private final int requestedRepeatCount;

    public NotEnoughInstructionsToRepeatException(int instructionsAvailable, int requestedRepeatCount) {
        this.instructionsAvailable = instructionsAvailable;
        this.requestedRepeatCount = requestedRepeatCount;
    }

    public int getInstructionsAvailable() {
        return instructionsAvailable;
    }

    public int getRequestedRepeatCount() {
        return requestedRepeatCount;
    }
}
