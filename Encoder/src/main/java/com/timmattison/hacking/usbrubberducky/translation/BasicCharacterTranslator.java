package com.timmattison.hacking.usbrubberducky.translation;

import com.timmattison.hacking.usbrubberducky.exceptions.UntranslatableCodeException;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;

import javax.inject.Inject;

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
    public KeyboardCode translate(char character) throws UntranslatableCodeException {
        // Get the code that corresponds to this character?
        KeyboardCode keyboardCode = keyboardCodes.get(String.valueOf(character));

        // Did we find it?
        if (keyboardCode == null) {
            // No, throw an exception.  We can't continue if we come across an untranslatable code
            throw new UntranslatableCodeException(keyboardCodes, character);
        }

        // Return the translated code to the caller
        return keyboardCode;
    }
}
