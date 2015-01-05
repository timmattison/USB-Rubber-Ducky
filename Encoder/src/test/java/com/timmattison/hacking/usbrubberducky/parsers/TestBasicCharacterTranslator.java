package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.exceptions.UntranslatableCodeException;
import com.timmattison.hacking.usbrubberducky.translation.BasicCharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by timmattison on 7/30/14.
 */
public class TestBasicCharacterTranslator {
    @Test
    public void testEmptyKeyboardCodesThrowsException() {
        KeyboardCodes emptyKeyboardCodes = new KeyboardCodes() {
            @Override
            public Map<String, KeyboardCode> get() {
                return new HashMap<String, KeyboardCode>();
            }
        };

        BasicCharacterTranslator basicCharacterTranslator = new BasicCharacterTranslator(emptyKeyboardCodes);

        try {
            basicCharacterTranslator.translate('a');
        } catch (UntranslatableCodeException e) {
            return;
        }

        Assert.fail("Exception not thrown when the keyboard codes structure is empty");
    }
}
