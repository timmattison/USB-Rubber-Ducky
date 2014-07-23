package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.constants.Whitespace;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.NopInstruction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/30/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class NopInstructionParser extends AbstractInstructionParser<NopInstruction> {
    private static final String name = "REM ";
    private static final String matchingRegex = "^" + name;

    @Override
    protected NopInstruction create(List<String> input) {
        return new NopInstruction();
    }

    @Override
    protected String getMatchingRegex() {
        return matchingRegex;
    }
}
