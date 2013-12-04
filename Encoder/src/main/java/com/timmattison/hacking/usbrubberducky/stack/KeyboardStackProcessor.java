package com.timmattison.hacking.usbrubberducky.stack;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;

import java.util.Stack;

/**
 * Created by timmattison on 7/22/14.
 */
public interface KeyboardStackProcessor {
    public KeyboardCode process(Stack<KeyboardCode> keyboardCodeStack);
}
