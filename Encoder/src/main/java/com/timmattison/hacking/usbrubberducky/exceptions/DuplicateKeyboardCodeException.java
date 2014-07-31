package com.timmattison.hacking.usbrubberducky.exceptions;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;

/**
 * Created by timmattison on 7/31/14.
 */
public class DuplicateKeyboardCodeException extends EncoderException {
    private final KeyboardCode keyboardCode;

    public DuplicateKeyboardCodeException(KeyboardCode keyboardCode) {
        this.keyboardCode = keyboardCode;
    }

    public KeyboardCode getKeyboardCode() {
        return keyboardCode;
    }
}
