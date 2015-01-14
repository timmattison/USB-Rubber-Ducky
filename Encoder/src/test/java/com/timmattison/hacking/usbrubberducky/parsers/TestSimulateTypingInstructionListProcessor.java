package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.instructions.BasicSimulateTypingInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.StringInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.DelayInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.SimulateTypingInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.StringInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.SimulateTypingInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.SimulateTypingInstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.translation.BasicCharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.USKeyboardCodes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

public class TestSimulateTypingInstructionListProcessor {
    private static final int MINIMUM_DELAY = 100;
    private static final int MAXIMUM_DELAY = 200;
    private static final String INPUT_STRING = "This is a test";
    private SimulateTypingInstructionListProcessor simulateTypingInstructionListProcessor;
    private DelayInstructionFactory delayInstructionFactory;
    private StringInstructionFactory stringInstructionFactory;
    private SimulateTypingInstructionFactory simulateTypingInstructionFactory;

    @Before
    public void setup() {
        simulateTypingInstructionFactory = new SimulateTypingInstructionFactory() {
            @Override
            public SimulateTypingInstruction create(@Assisted(MINIMUM_DELAY) int minimumDelay, @Assisted(MAXIMUM_DELAY) int maximumDelay, @Assisted(INPUT) String input) {
                return new BasicSimulateTypingInstruction(minimumDelay, maximumDelay, input);
            }
        };

        delayInstructionFactory = new DelayInstructionFactory() {
            @Override
            public DelayInstruction create(@Assisted("delayInMilliseconds") int delayInMilliseconds) {
                return new DelayInstruction(delayInMilliseconds);
            }
        };

        stringInstructionFactory = new StringInstructionFactory() {
            @Override
            public StringInstruction create(@Assisted("input") String input) {
                return new StringInstruction(new BasicCharacterTranslator(new USKeyboardCodes()), input);
            }
        };

        simulateTypingInstructionListProcessor = new SimulateTypingInstructionListProcessor(delayInstructionFactory, stringInstructionFactory);
    }

    @Test
    public void test1() {
        List<Instruction> instructionList = new ArrayList<Instruction>();

        instructionList.add(simulateTypingInstructionFactory.create(MINIMUM_DELAY, MAXIMUM_DELAY, INPUT_STRING));

        instructionList = simulateTypingInstructionListProcessor.process(instructionList);

        int totalInstructionCount = INPUT_STRING.length() * 2;
        Assert.assertThat(totalInstructionCount, is(instructionList.size()));

        for (int loop = 0; loop < totalInstructionCount / 2; loop++) {
            Instruction instruction = instructionList.get(loop * 2);
            Assert.assertThat(instruction, instanceOf(DelayInstruction.class));

            DelayInstruction delayInstruction = (DelayInstruction) instruction;
            int delayInMilliseconds = delayInstruction.getDelayValue();
            Assert.assertThat(delayInMilliseconds, is(greaterThanOrEqualTo(MINIMUM_DELAY)));
            Assert.assertThat(delayInMilliseconds, is(lessThan(MAXIMUM_DELAY)));

            instruction = instructionList.get((loop * 2) + 1);
            Assert.assertThat(instruction, instanceOf(StringInstruction.class));

            StringInstruction stringInstruction = (StringInstruction) instruction;
            Assert.assertThat(stringInstruction.getInput().length(), is(1));
        }
    }
}
