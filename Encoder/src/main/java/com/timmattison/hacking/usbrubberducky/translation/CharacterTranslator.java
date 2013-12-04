package com.timmattison.hacking.usbrubberducky.translation;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;

/**
 * Created by timmattison on 12/5/13.
 */
public interface CharacterTranslator {
    KeyboardCode translate(char character);
}
