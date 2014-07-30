package com.timmattison.hacking.usbrubberducky.parsers.advanced;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.parsers.simple.SimpleAbstractInstructionParser;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/27/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AdvancedAbstractInstructionParser<T extends Instruction> extends SimpleAbstractInstructionParser {
    @Inject
    public AdvancedAbstractInstructionParser(Preprocessor preprocessor) {
        super(preprocessor);
    }

    @Override
    protected Instruction create(List input) {
        throw new UnsupportedOperationException("create must never be called");
    }

    @Override
    protected String getMatchingRegex() {
        throw new UnsupportedOperationException("getMatchingRegex must never be called");
    }

    @Override
    public T parse(String input) {
        // Preprocess the input
        input = preprocessor.preprocess(input);

        return innerParse(input);
    }

    protected abstract T innerParse(String input);
}
