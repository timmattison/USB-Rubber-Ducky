package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.constants.Whitespace;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.RepeatInstructionFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class RepeatInstructionParser extends AbstractInstructionParser<RepeatInstruction> {
    private static final String name = "REPEAT";
    //private static final String matchingRegex = "^" + name + Whitespace.getWhitespaceCharClass() + "+" + "([0-9]+)" + "(" + getWhitespaceCharClass() + "+" + "([0-9]+))?";
    private static final String matchingRegex = "^" + name + Whitespace.getWhitespaceCharClass() + "+" + "([0-9]+)$";
    private final RepeatInstructionFactory repeatInstructionFactory;

    @Inject
    public RepeatInstructionParser(RepeatInstructionFactory repeatInstructionFactory) {
        this.repeatInstructionFactory = repeatInstructionFactory;
    }

    @Override
    protected RepeatInstruction create(List<String> input) {
        return repeatInstructionFactory.create(Integer.parseInt(input.get(0)));
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
