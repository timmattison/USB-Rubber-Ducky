package com.timmattison.hacking.usbrubberducky.parsers.regex;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.NopInstruction;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemInstructionParser extends RegexAbstractInstructionParser<NopInstruction> {
    private static final String name = "REM ";
    private static final String matchingRegex = "^" + name;

    @Inject
    public RemInstructionParser(Preprocessor preprocessor) {
        super(preprocessor);
    }

    @Override
    protected NopInstruction create(List<String> input) {
        return new NopInstruction();
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
