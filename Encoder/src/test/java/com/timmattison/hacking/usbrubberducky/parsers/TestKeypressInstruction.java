package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.instructions.KeypressInstruction;
import com.timmattison.hacking.usbrubberducky.support.BitCounter;
import com.timmattison.hacking.usbrubberducky.support.ParallelBitCounter;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifier;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardNonPrintableCodes;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.USKeyboardCodes;
import com.timmattison.hacking.usbrubberducky.translation.string.KeyboardUSNonShiftedCodes;
import com.timmattison.hacking.usbrubberducky.translation.string.KeyboardUSShiftedCodes;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by timmattison on 7/30/14.
 */
public class TestKeypressInstruction {
    private KeyboardCode getKeyboardCode1() {
        return KeyboardUSShiftedCodes.values()[0].getValue();
    }

    private KeyboardCode getKeyboardCode2() {
        return KeyboardUSNonShiftedCodes.values()[0].getValue();
    }

    private KeyboardCode getKeyboardCode3() {
        return KeyboardNonPrintableCodes.values()[0].getValue();
    }

    @Test
    public void testDuplicatesThrowsException() {
        Stack<KeyboardCode> keyboardCodeStack = new Stack<KeyboardCode>();
        keyboardCodeStack.add(getKeyboardCode1());
        keyboardCodeStack.add(getKeyboardCode1());

        KeypressInstruction keypressInstruction = new KeypressInstruction(getBitCounter(), keyboardCodeStack);

        try {
            keypressInstruction.getEncodedInstruction();
        } catch (Exception e) {
            return;
        }

        // No exception thrown, duplicate detection doesn't work
        Assert.fail("Duplicate detection did not work");
    }

    private BitCounter getBitCounter() {
        return new ParallelBitCounter();
    }

    @Test
    public void testNoDuplicatesDoesNotThrowException() {
        Stack<KeyboardCode> keyboardCodeStack = new Stack<KeyboardCode>();
        keyboardCodeStack.add(getKeyboardCode1());
        keyboardCodeStack.add(getKeyboardCode2());

        KeypressInstruction keypressInstruction = new KeypressInstruction(getBitCounter(), keyboardCodeStack);

        try {
            keypressInstruction.getEncodedInstruction();
        } catch (Exception e) {
            // Exception thrown, duplicate detection doesn't work
            Assert.fail("Duplicate detection did not work");
        }
    }

    @Test
    public void testAltAndLeftAltTriggersBitCounter() {
        Stack<KeyboardCode> keyboardCodeStack = new Stack<KeyboardCode>();
        keyboardCodeStack.add(KeyboardModifier.ALT.getValue());
        keyboardCodeStack.add(KeyboardModifier.LEFT_ALT.getValue());
        keyboardCodeStack.add(KeyboardUSNonShiftedCodes.KeyboardE.getValue());

        KeypressInstruction keypressInstruction = new KeypressInstruction(getBitCounter(), keyboardCodeStack);

        try {
            keypressInstruction.getEncodedInstruction();
        } catch (Exception e) {
            return;
        }

        // No exception thrown, bit counter trigger did not work
        Assert.fail("Bit counter trigger did not work");
    }
}
