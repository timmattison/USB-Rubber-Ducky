package com.timmattison.hacking.usbrubberducky.translation.codes;

/**
 * Created by timmattison on 12/5/13.
 */
public enum KeyboardModifier implements ReturnsKeyboardCode {
    CTRL("CTRL", 0x01),
    CONTROL("CONTROL", 0x01),
    SHIFT("SHIFT", 0x02),
    ALT("ALT", 0x04),
    GUI("GUI", 0x08),
    WINDOWS("WINDOWS", 0x08),
    COMMAND("COMMAND", 0x08),
    LEFT_CTRL("LEFTCTRL", 0x01),
    LEFT_SHIFT("LEFTSHIFT", 0x02),
    LEFT_ALT("LEFTALT", 0x04),
    LEFT_GUI("LEFTGUI", 0x08),
    RIGHT_CTRL("RIGHTCTRL", 0x10),
    RIGHT_SHIFT("RIGHTSHIFT", 0x20),
    RIGHT_ALT("RIGHTALT", 0x40),
    RIGHT_GUI("RIGHTGUI", 0x80);

    private final KeyboardCode value;

    KeyboardModifier(String name, int value) {
        this.value = new KeyboardCode(name, (byte) 0x00, (byte) value);
    }

    public KeyboardCode getValue() {
        return value;
    }
}
