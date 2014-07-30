package com.timmattison.hacking.usbrubberducky.preprocessors;

/**
 * This preprocessor converts some legacy script syntax to the standard syntax
 * <p/>
 * Created by timmattison on 7/30/14.
 */
public class LegacyPreprocessor implements Preprocessor {
    @Override
    public String preprocess(String input) {
        input = input.replaceAll("^CTRL-SHIFT", "CTRL SHIFT");
        input = input.replaceAll("^CONTROL-SHIFT", "CONTROL SHIFT");
        input = input.replaceAll("^DEFAULTDELAY", "DEFAULT_DELAY");
        return input;
    }
}
