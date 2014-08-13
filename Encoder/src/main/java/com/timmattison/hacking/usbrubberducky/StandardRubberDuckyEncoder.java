package com.timmattison.hacking.usbrubberducky;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionListFactory;
import com.timmattison.hacking.usbrubberducky.parsers.InstructionParser;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 10/25/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class StandardRubberDuckyEncoder implements RubberDuckyEncoder {
    private final Set<InstructionParser> instructionParserSet;
    private final InstructionListFactory instructionListFactory;

    @Inject
    public StandardRubberDuckyEncoder(Set<InstructionParser> instructionParserSet, InstructionListFactory instructionListFactory) {
        this.instructionParserSet = instructionParserSet;
        this.instructionListFactory = instructionListFactory;
    }

    @Override
    public byte[] encode(String[] input) throws IOException {
        // Create a new instruction list
        InstructionList instructionList = instructionListFactory.create();

        // Loop through each input line
        for (String line : input) {
            // Start out with a null instruction
            Instruction instruction = null;

            // Loop through each parser
            for (InstructionParser instructionParser : instructionParserSet) {
                // Run the parser on this line
                instruction = instructionParser.parse(line);

                // Was the parser successful?
                if (instruction != null) {
                    // Yes, exit the for loop
                    break;
                }
            }

            // Did we find a parser that could handle this line?
            if (instruction == null) {
                // No, throw an exception
                throw new UnsupportedOperationException("Couldn't process instruction [" + line + "]");
            }

            // Add the instruction to the list
            instructionList.addInstruction(instruction);
        }

        // Get the byte array from this instruction list
        return instructionList.getBytes();
    }
}
