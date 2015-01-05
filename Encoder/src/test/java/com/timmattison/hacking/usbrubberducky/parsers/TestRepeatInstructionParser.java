package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.BasicRepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.RepeatInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.parsers.regex.RepeatInstructionParser;
import com.timmattison.hacking.usbrubberducky.preprocessors.LegacyPreprocessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/4/13
 * Time: 6:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestRepeatInstructionParser {
    private RepeatInstructionParser repeatInstructionParser;
    private RepeatInstructionFactory repeatInstructionFactory;

    @Before
    public void setup() {
        repeatInstructionFactory = new RepeatInstructionFactory() {
            @Override
            public RepeatInstruction create(@Assisted(REPEAT_COUNT) int repeatCount, @Assisted(INSTRUCTION_COUNT) int instructionCount) {
                return new BasicRepeatInstruction(repeatCount, instructionCount);
            }
        };

        repeatInstructionParser = new RepeatInstructionParser(new LegacyPreprocessor(), repeatInstructionFactory);
    }

    @Test
    public void testSimpleRepeat1() {
        int repeatCount = 1;

        innerRepeatTest(repeatCount);
    }

    @Test
    public void testSimpleRepeat2() {
        int repeatCount = 999;

        innerRepeatTest(repeatCount);
    }

    private void innerRepeatTest(int repeatCount) {
        String testString = "REPEAT " + repeatCount;

        RepeatInstruction repeatInstruction = repeatInstructionParser.parse(testString);

        Assert.assertThat(repeatInstruction, notNullValue());
        Assert.assertThat(repeatInstruction.getRepeatCount(), is(repeatCount));
        Assert.assertThat(repeatInstruction.getInstructionCount(), is(1));
    }

    @Test
    public void testNotHandlingMultipleInstructionRepeatsYet() {
        String test2String = "REPEAT 1 1";

        RepeatInstruction repeatInstruction = repeatInstructionParser.parse(test2String);

        Assert.assertThat(repeatInstruction, nullValue());
    }

    @Test
    public void testInvalidInput() {
        String test3String = "REPEAT 1 1 1";

        RepeatInstruction repeatInstruction = repeatInstructionParser.parse(test3String);

        Assert.assertThat(repeatInstruction, nullValue());
    }
}
