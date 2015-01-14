package com.timmattison.hacking.usbrubberducky.parsers.regex;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.constants.Whitespace;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.DelayInstructionFactory;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class DelayInstructionParser extends RegexAbstractInstructionParser<DelayInstruction> {
    private static final String name = "DELAY";
    private static final String matchingRegex = "^" + name + Whitespace.getWhitespaceCharClass() + "+" + NUMERIC_ARGUMENT + "$";
    private final DelayInstructionFactory delayInstructionFactory;

    @Inject
    public DelayInstructionParser(Preprocessor preprocessor, DelayInstructionFactory delayInstructionFactory) {
        super(preprocessor);
        this.delayInstructionFactory = delayInstructionFactory;
    }

    @Override
    protected DelayInstruction create(List<String> input) {
        // Get the integer value from the input that contains the delay amount and create a delay instruction with it
        return delayInstructionFactory.create(Integer.parseInt(input.get(0)));
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
