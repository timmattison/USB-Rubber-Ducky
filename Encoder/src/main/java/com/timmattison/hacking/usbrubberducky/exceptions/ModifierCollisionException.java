package com.timmattison.hacking.usbrubberducky.exceptions;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;

import java.util.Stack;

/**
 * Created by timmattison on 7/31/14.
 */
public class ModifierCollisionException extends EncoderException {
    private final Stack<KeyboardCode> keyboardCodeStack;

    public ModifierCollisionException(Stack<KeyboardCode> keyboardCodeStack) {
        this.keyboardCodeStack = keyboardCodeStack;
    }

    public Stack<KeyboardCode> getKeyboardCodeStack() {
        return keyboardCodeStack;
    }
}
