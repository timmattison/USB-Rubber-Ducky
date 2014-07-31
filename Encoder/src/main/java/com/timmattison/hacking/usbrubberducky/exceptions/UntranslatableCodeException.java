package com.timmattison.hacking.usbrubberducky.exceptions;

import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;

/**
 * Created by timmattison on 7/31/14.
 */
public class UntranslatableCodeException extends Throwable {
    private final KeyboardCodes availableKeyboardCodes;
    private final char character;

    public UntranslatableCodeException(KeyboardCodes keyboardCodes, char character) {
        this.availableKeyboardCodes = keyboardCodes;
        this.character = character;
    }

    public KeyboardCodes getAvailableKeyboardCodes() {
        return availableKeyboardCodes;
    }

    public char getCharacter() {
        return character;
    }
}
