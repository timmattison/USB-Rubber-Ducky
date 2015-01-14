package com.timmattison.hacking.usbrubberducky.instructions;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.exceptions.DuplicateKeyboardCodeException;
import com.timmattison.hacking.usbrubberducky.exceptions.ModifierCollisionException;
import com.timmattison.hacking.usbrubberducky.instructions.factories.KeypressInstructionFactory;
import com.timmattison.hacking.usbrubberducky.support.BitCounter;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;

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
    private final BitCounter bitCounter;
    private final Stack<KeyboardCode> keyboardCodeStack;

    @Inject
    public KeypressInstruction(BitCounter bitCounter, @Assisted(KeypressInstructionFactory.KEYBOARD_CODE_STACK) Stack<KeyboardCode> keyboardCodeStack) {
        this.bitCounter = bitCounter;
        this.keyboardCodeStack = keyboardCodeStack;
    }

    @Override
    public byte[] getEncodedInstruction() throws DuplicateKeyboardCodeException, ModifierCollisionException {
        // The final output value
        KeyboardCode output = null;

        // The keyboard codes we've used already
        HashSet<KeyboardCode> usedKeyboardCodes = new HashSet<KeyboardCode>();

        // Clone the stack that we're working with so that we can re-run this instruction if necessary
        Stack<KeyboardCode> tempKeyboardCodeStack = (Stack<KeyboardCode>) keyboardCodeStack.clone();

        // Loop while there are still codes on the stack
        while (!tempKeyboardCodeStack.empty()) {
            // Pop off the top element
            KeyboardCode keyboardCode = tempKeyboardCodeStack.pop();

            // Make sure there are no duplicates
            forceNoDuplicates(usedKeyboardCodes, keyboardCode);

            // Get the bit count before we add/combine this code
            int bitCountBefore = getBitCount(output);

            // Do we have any keys yet?
            if (output == null) {
                // No, just copy this code
                output = keyboardCode;
            } else {
                // Yes, it with the previous keys
                output = output.combine(keyboardCode);
            }

            // Get the bit count after we added/combined this code
            int bitCountAfter = getBitCount(output);

            // Are the bit counts the same?
            if (bitCountBefore == bitCountAfter) {
                // Yes, this means that multiple codes overlapped
                throw new ModifierCollisionException((Stack<KeyboardCode>) keyboardCodeStack.clone());
            }
        }

        // Return the bytes to the caller
        return output.getBytes();
    }

    private int getBitCount(KeyboardCode output) {
        if (output == null) {
            return 0;
        }

        return bitCounter.countBits(output.getFirstByte()) + bitCounter.countBits(output.getSecondByte());
    }

    private void forceNoDuplicates(Set<KeyboardCode> usedKeyboardCodes, KeyboardCode keyboardCode) throws DuplicateKeyboardCodeException {
        // Does the set already contain this keyboard code?
        if (usedKeyboardCodes.contains(keyboardCode)) {
            // Yes, throw an exception
            throw new DuplicateKeyboardCodeException(keyboardCode);
        }

        // Add the code to the set
        usedKeyboardCodes.add(keyboardCode);
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
