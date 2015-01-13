package com.timmattison.hacking.usbrubberducky.parsers.regex;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.constants.Whitespace;
import com.timmattison.hacking.usbrubberducky.instructions.factories.RepeatInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class MultipleRepeatInstructionParser extends RegexAbstractInstructionParser<RepeatInstruction> {
    private static final String name = "REPEAT";
    private static final String matchingRegex = "^" + name + Whitespace.getWhitespaceCharClass() + "+" + "([0-9]+)" + Whitespace.getWhitespaceCharClass() + "+" + "([0-9]+)$";
    private final RepeatInstructionFactory repeatInstructionFactory;

    @Inject
    public MultipleRepeatInstructionParser(Preprocessor preprocessor, RepeatInstructionFactory repeatInstructionFactory) {
        super(preprocessor);
        this.repeatInstructionFactory = repeatInstructionFactory;
    }

    @Override
    protected RepeatInstruction create(List<String> input) {
        // Parse the number of times we should repeat and create the instruction using the factory
        int numberOfTimesToRepeat = Integer.parseInt(input.get(0));
        int numberOfInstructionsToRepeat = Integer.parseInt(input.get(1));
        return repeatInstructionFactory.create(numberOfTimesToRepeat, numberOfInstructionsToRepeat);
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
