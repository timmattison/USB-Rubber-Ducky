__author__ = 'timmattison'

base_modifiers = ["CTRL", "CONTROL", "SHIFT", "ALT", "GUI", "WINDOWS", "COMMAND"]

left_modifiers = ["LEFTCTRL", "LEFTSHIFT", "LEFTALT", "LEFTGUI"]
right_modifiers = ["RIGHTCTRL", "RIGHTSHIFT", "RIGHTALT", "RIGHTGUI"]

us_non_shifted_codes = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                        't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'SPACE', '-',
                        '=', '[', ']', '\\', ';', '\'', '`', ',', '.', '/']

print "REM All US non-shifted codes"

for code in us_non_shifted_codes:
    print "STRING " + code

print "REM All US non-shifted codes with base modifiers"

for modifier in base_modifiers:
    for code in us_non_shifted_codes:
        print modifier + " " + code

