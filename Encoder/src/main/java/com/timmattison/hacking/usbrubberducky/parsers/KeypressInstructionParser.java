package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.KeypressInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.KeypressInstructionFactory;
import com.timmattison.hacking.usbrubberducky.stack.KeyboardStackProcessor;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifier;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/27/13
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeypressInstructionParser implements InstructionParser<KeypressInstruction> {
    private final KeypressInstructionFactory keypressInstructionFactory;
    private final KeyboardCodes keyboardCodes;

    @Inject
    public KeypressInstructionParser(KeypressInstructionFactory keypressInstructionFactory, KeyboardCodes keyboardCodes) {
        this.keypressInstructionFactory = keypressInstructionFactory;
        this.keyboardCodes = keyboardCodes;
    }

    @Override
    public KeypressInstruction parse(String input) {
        String[] inputChunks = input.split(" ");

        Stack<KeyboardCode> keyboardCodeStack = new Stack<KeyboardCode>();

        for (String inputChunk : inputChunks) {
            String currentChunk = inputChunk.trim();

            KeyboardCode keyboardCode = getKeyboardCode(currentChunk);

            // Did we find this mapping?
            if (keyboardCode == null) {
                // No, maybe it is a modifier
                keyboardCode = getKeyboardModifier(currentChunk);
            }

            // Did we still not find this mapping?
            if (keyboardCode == null) {
                // No, this shouldn't happen
                throw new UnsupportedOperationException("Unknown modifier or keyboard code [" + currentChunk + "]");
            }

            // Push this code onto the stack
            keyboardCodeStack.push(keyboardCode);
        }

        // Get the keypress instruction for this code
        return keypressInstructionFactory.create(keyboardCodeStack);
    }

    private KeyboardCode getKeyboardCode(String codeString) {
        return keyboardCodes.get().get(codeString);
    }

    private KeyboardCode getKeyboardModifier(String codeString) {
        return KeyboardModifier.valueOf(codeString).getValue();
    }
}
