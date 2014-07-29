package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.instructions.NopInstruction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlankLineInstructionParser extends AbstractInstructionParser<NopInstruction> {
    private static final String matchingRegex = "^\\s*$";

    @Override
    protected NopInstruction create(List<String> input) {
        return new NopInstruction();
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
