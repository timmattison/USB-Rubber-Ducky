package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.constants.Whitespace;
import com.timmattison.hacking.usbrubberducky.instructions.DefaultDelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultDelayInstructionParser extends AbstractInstructionParser<DefaultDelayInstruction> {
    private static final String name = "DEFAULT_DELAY";
    private static final String matchingRegex = "^" + name + Whitespace.getWhitespaceCharClass() + "+" + "([0-9]+)$";

    @Override
    protected DefaultDelayInstruction create(List<String> input) {
        return new DefaultDelayInstruction(Integer.parseInt(input.get(0)));
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
