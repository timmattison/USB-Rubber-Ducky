package com.timmattison.hacking.usbrubberducky.preprocessors;

/**
 * Created by timmattison on 7/30/14.
 */
public class NopPreprocessor implements Preprocessor {
    @Override
    public String preprocess(String input) {
        return input;
    }
}
