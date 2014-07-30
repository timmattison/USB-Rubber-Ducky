__author__ = 'timmattison'

# TODO: Add all single chars
valid_alt_codes = ["END", "ESCAPE", "ESC", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12",
                   "SPACE", "TAB"]

valid_single_codes = ["BREAK", "PAUSE", "CAPSLOCK", "DELETE", "DOWNARROW", "DOWN", "END", "ESCAPE", "ESC", "F1", "F2",
                      "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "HOME" "INSERT" "LEFTARROW",
                      "LEFT", "MENU", "APP", "NUMLOCK", "PAGEDOWN", "PAGEUP", "PRINTSCREEN", "RIGHTARROW", "RIGHT",
                      "SCROLLLOCK", "SPACE", "TAB", "UPARROW", "UP", "WINDOWS", "GUI"]

# TODO: Add all single chars
valid_control_codes = ["BREAK", "PAUSE", "ESCAPE", "ESC", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10",
                       "F11", "F12"]

valid_shift_codes = ["DELETE", "HOME", "INSERT", "PAGEUP", "PAGEDOWN", "WINDOWS", "GUI", "UPARROW", "DOWNARROW",
                     "LEFTARROW", "RIGHTARROW", "TAB"]

alts = ["ALT"]
controls = ["CONTROL", "CTRL"]
shifts = ["SHIFT"]

def generate_all_codes(first_codes, second_codes):
    for first_code in first_codes:
        for second_code in second_codes:
            print first_code + " " + second_code

generate_all_codes(alts, valid_alt_codes)
generate_all_codes(controls, valid_control_codes)
generate_all_codes(shifts, valid_shift_codes)
