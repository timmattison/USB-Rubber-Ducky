package com.timmattison.hacking.usbrubberducky.parsers.regex;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.constants.Whitespace;
import com.timmattison.hacking.usbrubberducky.instructions.factories.SimulateTypingInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.SimulateTypingInstruction;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;

import java.util.List;

public class SimulateTypingInstructionParser extends RegexAbstractInstructionParser<SimulateTypingInstruction> {
    private static final String name = "SIMULATE_TYPING";
    private static final String matchingRegex = "^" + name + Whitespace.getWhitespaceCharClass() + "+" + NUMERIC_ARGUMENT + Whitespace.getWhitespaceCharClass() + "+" + NUMERIC_ARGUMENT + Whitespace.getWhitespaceCharClass() + "{1}" + THE_REST;
    private final SimulateTypingInstructionFactory simulateTypingInstructionFactory;

    @Inject
    public SimulateTypingInstructionParser(Preprocessor preprocessor, SimulateTypingInstructionFactory simulateTypingInstructionFactory) {
        super(preprocessor);
        this.simulateTypingInstructionFactory = simulateTypingInstructionFactory;
    }

    @Override
    protected SimulateTypingInstruction create(List<String> input) {
        // Get the integer value from the input that contains the delay amount and create a delay instruction with it
        return simulateTypingInstructionFactory.create(Integer.parseInt(input.get(0)), Integer.parseInt(input.get(1)), input.get(2));
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
