package com.timmattison.hacking.usbrubberducky.parsers.advanced;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.KeypressInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.KeypressInstructionFactory;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifier;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifierMap;
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
public class KeypressInstructionParser extends NonRegexAbstractInstructionParser<KeypressInstruction> {
    private final KeypressInstructionFactory keypressInstructionFactory;
    private final KeyboardCodes keyboardCodes;
    private final KeyboardModifierMap keyboardModifierMap;

    @Inject
    public KeypressInstructionParser(Preprocessor preprocessor, KeypressInstructionFactory keypressInstructionFactory, KeyboardCodes keyboardCodes, KeyboardModifierMap keyboardModifierMap) {
        super(preprocessor);
        this.keypressInstructionFactory = keypressInstructionFactory;
        this.keyboardCodes = keyboardCodes;
        this.keyboardModifierMap = keyboardModifierMap;
    }

    @Override
    protected KeypressInstruction innerParse(String input) {
        // Split up the input into chunks separated by spaces
        String[] inputChunks = input.split(" ");

        // Create a stack to hold our keyboard codes
        Stack<KeyboardCode> keyboardCodeStack = new Stack<KeyboardCode>();

        // Loop through each chunk
        for (int loop = 0; loop < inputChunks.length; loop++) {
            String inputChunk = inputChunks[loop];

            // Trim the whitespace on the chunk.  Whitespace is not important for keypresses.
            String currentChunk = inputChunk.trim();

            KeyboardCode keyboardCode;

            // Is this the last keyboard code?
            if (loop == (inputChunks.length - 1)) {
                // Yes, it MUST be a real key
                keyboardCode = getKeyboardCode(currentChunk);
            } else {
                // No, it MUST be a modifier
                keyboardCode = getKeyboardModifier(currentChunk);
            }

            // Did we find this mapping?
            if (keyboardCode == null) {
                // No, we can't process it.  Return NULL to tell the caller that our processing failed.
                return null;
            }

            // Push this code onto the stack
            keyboardCodeStack.push(keyboardCode);
        }

        // Get the keypress instruction for this code
        return keypressInstructionFactory.create(keyboardCodeStack);
    }

    private KeyboardCode getKeyboardCode(String codeString) {
        return keyboardCodes.get(codeString);
    }

    private KeyboardCode getKeyboardModifier(String codeString) {
        return keyboardModifierMap.get(codeString);
    }
}
