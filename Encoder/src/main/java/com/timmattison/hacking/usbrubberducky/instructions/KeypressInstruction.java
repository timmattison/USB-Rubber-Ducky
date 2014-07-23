package com.timmattison.hacking.usbrubberducky.instructions;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifier;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/3/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeypressInstruction implements Instruction {
    private final Stack<KeyboardCode> keyboardCodeStack;
    private Set<KeyboardCode> usedKeyboardCodes;

    @Inject
    public KeypressInstruction(@Assisted("keyboardCodeStack") Stack<KeyboardCode> keyboardCodeStack) {
        this.keyboardCodeStack = keyboardCodeStack;
    }

    @Override
    public byte[] getEncodedInstruction() {
        // Loop through the keyboard codes
        boolean first = true;
        boolean finalKeyIsLeftGui = false;

        // The final output value
        KeyboardCode output = null;

        // The keyboard codes we've used already
        usedKeyboardCodes = new HashSet<KeyboardCode>();

        while (!keyboardCodeStack.empty()) {
            KeyboardCode keyboardCode = keyboardCodeStack.pop();

            // Make sure there are no duplicates
            forceNoDuplicates(keyboardCode);

            // Is this the first key?
            if (!first) {
                // No, is it a modifier?
                if (keyboardCode.getFirstByte() != 0x00) {
                    // No, throw an exception
                    throw new UnsupportedOperationException("All leading key codes must be modifiers");
                }

                // Is this a GUI key and did we already see one as the final key?
                if (isLeftGuiKey(keyboardCode) && finalKeyIsLeftGui) {
                    // Yes, throw an exception
                    throw new UnsupportedOperationException("GUI key cannot be the final keypress and a modifier at the same time");
                }

                // Combine it with the previous keys
                output = output.combine(keyboardCode);
            } else {
                // Yes, copy it
                output = keyboardCode;

                // Is this the left GUI key?
                if (isLeftGuiKey(output)) {
                    // Yes, indicate that it is
                    finalKeyIsLeftGui = true;
                }

                // We are past the first key
                first = false;
            }
        }

        // Return the bytes to the caller
        return output.getBytes();
    }

    private void forceNoDuplicates(KeyboardCode keyboardCode) {
        int sizeBeforeAdding = usedKeyboardCodes.size();

        usedKeyboardCodes.add(keyboardCode);

        int sizeAfterAdding = usedKeyboardCodes.size();

        if(sizeAfterAdding == sizeBeforeAdding) {
            throw new UnsupportedOperationException("Duplicate keyboard code in a single line [" + keyboardCode + "]");
        }
    }

    private boolean isLeftGuiKey(KeyboardCode keyboardCode) {
        return (keyboardCode.equals(KeyboardModifier.GUI.getValue())) || (keyboardCode.equals(KeyboardModifier.LEFT_GUI));
    }

    private boolean isRightGuiKey(KeyboardCode keyboardCode) {
        return keyboardCode.equals(KeyboardModifier.RIGHT_GUI.getValue());
    }
}
