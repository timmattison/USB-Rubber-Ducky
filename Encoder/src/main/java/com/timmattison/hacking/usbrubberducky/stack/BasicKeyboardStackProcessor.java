package com.timmattison.hacking.usbrubberducky.stack;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by timmattison on 7/22/14.
 */
public class BasicKeyboardStackProcessor implements KeyboardStackProcessor {
    public KeyboardCode process(Stack<KeyboardCode> keyboardCodeStack) {
        KeyboardCode output = null;

        Set<KeyboardCode> usedKeyboardCodes = new HashSet<KeyboardCode>();

        while(!keyboardCodeStack.empty()) {
            KeyboardCode currentKeyboardCode = keyboardCodeStack.pop();

            int sizeBeforeAdding = usedKeyboardCodes.size();

            usedKeyboardCodes.add(currentKeyboardCode);

            int sizeAfterAdding = usedKeyboardCodes.size();

            if(sizeAfterAdding == sizeBeforeAdding) {
                throw new UnsupportedOperationException("Duplicate keyboard code in a single line [" + currentKeyboardCode + "]");
            }

            if(output == null) {
                output = currentKeyboardCode;
            }
            else {
                output = output.combine(currentKeyboardCode);
            }
        }

        return output;
    }
}
