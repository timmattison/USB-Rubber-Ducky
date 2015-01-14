package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.instructions.BasicSimulateTypingInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.SimulateTypingInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.SimulateTypingInstruction;
import com.timmattison.hacking.usbrubberducky.parsers.regex.SimulateTypingInstructionParser;
import com.timmattison.hacking.usbrubberducky.preprocessors.LegacyPreprocessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class TestSimulateTypingInstructionParser {
    private SimulateTypingInstructionParser simulateTypingInstructionParser;
    private SimulateTypingInstructionFactory simulateTypingInstructionFactory;

    @Before
    public void setup() {
        simulateTypingInstructionFactory = new SimulateTypingInstructionFactory() {
            @Override
            public SimulateTypingInstruction create(int minimumDelay, int maximumDelay, String input) {
                return new BasicSimulateTypingInstruction(minimumDelay, maximumDelay, input);
            }
        };

        simulateTypingInstructionParser = new SimulateTypingInstructionParser(new LegacyPreprocessor(), simulateTypingInstructionFactory);
    }

    @Test
    public void shouldReturnValidInstruction() {
        int minimumDelay = 100;
        int maximumDelay = 200;
        String input = "This is a test";

        innerSimulateTypingTest(minimumDelay, maximumDelay, input);
    }

    private void innerSimulateTypingTest(int minimumDelay, int maximumDelay, String input) {
        String testString = "SIMULATE_TYPING " + minimumDelay + " " + maximumDelay + " " + input;

        SimulateTypingInstruction simulateTypingInstruction = simulateTypingInstructionParser.parse(testString);

        Assert.assertThat(simulateTypingInstruction, notNullValue());
        Assert.assertThat(simulateTypingInstruction.getMinimumDelay(), is(minimumDelay));
        Assert.assertThat(simulateTypingInstruction.getMaximumDelay(), is(maximumDelay));
        Assert.assertThat(simulateTypingInstruction.getInput(), is(input));
    }

    @Test
    public void shouldReturnNull() {
        String input = "SIMULATE_TYPING 1 2 ";
        SimulateTypingInstruction simulateTypingInstruction = simulateTypingInstructionParser.parse(input);

        Assert.assertThat(simulateTypingInstruction, nullValue());
    }
}
