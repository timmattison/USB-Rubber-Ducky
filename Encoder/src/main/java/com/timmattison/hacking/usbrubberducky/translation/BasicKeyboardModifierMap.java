package com.timmattison.hacking.usbrubberducky.translation;

import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardCode;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifier;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifierMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by timmattison on 1/14/15.
 */
public class BasicKeyboardModifierMap implements KeyboardModifierMap {
    private Map<String, KeyboardCode> keyboardModifierMap;

    public synchronized void setup() {
        // Has the keyboard modifier map been built already?
        if (keyboardModifierMap == null) {
            // No, build it
            keyboardModifierMap = new HashMap<String, KeyboardCode>();

            // Loop through each keyboard modifier and add it and its name to a map so we can search them quickly.
            for (KeyboardModifier keyboardModifier : KeyboardModifier.values()) {
                keyboardModifierMap.put(keyboardModifier.name(), keyboardModifier.getValue());
            }
        }
    }

    @Override
    public KeyboardCode get(String string) {
        setup();

        return keyboardModifierMap.get(string);
    }
}
