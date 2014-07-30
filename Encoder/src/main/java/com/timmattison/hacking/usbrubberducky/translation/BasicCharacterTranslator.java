package com.timmattison.hacking.usbrubberducky.translation;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by timmattison on 12/5/13.
 */
public class BasicCharacterTranslator implements CharacterTranslator {
    private final KeyboardCodes keyboardCodes;

    @Inject
    public BasicCharacterTranslator(KeyboardCodes keyboardCodes) {
        this.keyboardCodes = keyboardCodes;
    }

    @Override
    public KeyboardCode translate(char character) {
        KeyboardCode keyboardCode = keyboardCodes.get().get(String.valueOf(character));

        if (keyboardCode == null) {
            throw new UnsupportedOperationException("Character [" + character + "] not found in keyboard lookup table");
        }

        return keyboardCode;
    }
}
