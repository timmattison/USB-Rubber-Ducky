package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.StringInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.StringInstructionFactory;
import com.timmattison.hacking.usbrubberducky.translation.BasicCharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.USKeyboardCodes;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/4/13
 * Time: 6:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestStringInstructionParser {
    private StringInstructionParser stringInstructionParser;

    @Before
    public void setup() {
        StringInstructionFactory stringInstructionFactory = new StringInstructionFactory() {
            @Override
            public StringInstruction create(@Assisted("input") String input) {
                return new StringInstruction(new BasicCharacterTranslator(new USKeyboardCodes()), input);
            }
        };

        stringInstructionParser = new StringInstructionParser(stringInstructionFactory);
    }

    @Test
    public void testValidInput() {
        String test1String = "STRING asdfasdfasdf";

        StringInstruction stringInstruction = stringInstructionParser.parse(test1String);

        Assert.assertNotNull(stringInstruction);
    }

    @Test
    public void testInvalidInput() {
        String test1String = "STRINGasdfasdfasdf";

        StringInstruction stringInstruction = stringInstructionParser.parse(test1String);

        Assert.assertNull(stringInstruction);
    }

    @Test
    public void testInput1() throws IOException {
        String[] input = TestAgainstFiles.getInputFile(1);
        byte[] expectedOutput = TestAgainstFiles.getOutputFile(1);

        byte[] actualOutput = stringInstructionParser.parse(input[0]).getEncodedInstruction();

        Assert.assertTrue(Arrays.equals(expectedOutput, actualOutput));
    }
}
