package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.KeypressInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.KeypressInstructionFactory;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifier;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;

import java.util.HashMap;
import java.util.Map;
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
    private Map<String, KeyboardCode> keyboardModifierMap;

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

            boolean last = false;

            // Is this the last keyboard code?
            if(inputChunk.equals(inputChunks[inputChunks.length - 1])) {
                last = true;
            }

            boolean only = false;

            // Is this the only keyboard code?
            if(inputChunks.length == 1) {
                only = true;
            }

            KeyboardCode keyboardCode;

            if(last || only) {
                // Yes, it must be a real key
                keyboardCode = getKeyboardCode(currentChunk);
            }
            else {
                // No, it must be a modifier
                keyboardCode = getKeyboardModifier(currentChunk);
            }

            // Did we still not find this mapping?
            if (keyboardCode == null) {
                // No, we can't process it
                return null;
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
        return getKeyboardModifierMap().get(codeString);
    }

    private Map<String, KeyboardCode> getKeyboardModifierMap() {
        if (keyboardModifierMap == null) {
            keyboardModifierMap = new HashMap<String, KeyboardCode>();

            for (KeyboardModifier keyboardModifier : KeyboardModifier.values()) {
                keyboardModifierMap.put(keyboardModifier.name(), keyboardModifier.getValue());
            }
        }

        return keyboardModifierMap;
    }
}
