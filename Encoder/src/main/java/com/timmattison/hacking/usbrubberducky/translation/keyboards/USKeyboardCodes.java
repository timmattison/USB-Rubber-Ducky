package com.timmattison.hacking.usbrubberducky.translation.keyboards;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardNonPrintableCodes;
import com.timmattison.hacking.usbrubberducky.translation.string.KeyboardUSNonShiftedCodes;
import com.timmattison.hacking.usbrubberducky.translation.string.KeyboardUSShiftedCodes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by timmattison on 12/9/13.
 */
public class USKeyboardCodes extends AbstractKeyboardCodes implements KeyboardCodes {
    private Map<String, KeyboardCode> keyboardCodes;

    @Override
    public Map<String, KeyboardCode> get() {
        if (keyboardCodes == null) {
            keyboardCodes = new HashMap<String, KeyboardCode>();

            addKeyboardValues(KeyboardUSShiftedCodes.values(), keyboardCodes);
            addKeyboardValues(KeyboardUSNonShiftedCodes.values(), keyboardCodes);
            addKeyboardValues(KeyboardNonPrintableCodes.values(), keyboardCodes);
        }

        return keyboardCodes;
    }
}
