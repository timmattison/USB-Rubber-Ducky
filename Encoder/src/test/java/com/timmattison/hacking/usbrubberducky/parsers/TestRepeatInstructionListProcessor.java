package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.exceptions.NotEnoughInstructionsToRepeatException;
import com.timmattison.hacking.usbrubberducky.instructions.BasicRepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.RepeatInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.RepeatInstructionListProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/4/13
 * Time: 6:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestRepeatInstructionListProcessor {
    private RepeatInstructionListProcessor repeatInstructionListProcessor;
    private RepeatInstructionFactory repeatInstructionFactory;

    @Before
    public void setup() {
        repeatInstructionFactory = new RepeatInstructionFactory() {
            @Override
            public RepeatInstruction create(@Assisted(REPEAT_COUNT) int repeatCount, @Assisted(INSTRUCTION_COUNT) int instructionCount) {
                return new BasicRepeatInstruction(repeatCount, instructionCount);
            }
        };

        repeatInstructionListProcessor = new RepeatInstructionListProcessor();
    }

    private DelayInstruction getDelayInstruction(int delayInMilliseconds) {
        return new DelayInstruction(delayInMilliseconds);
    }

    @Test
    public void testSingleRepeatRepeatsOnce() throws NotEnoughInstructionsToRepeatException {
        List<Instruction> instructionList = new ArrayList<Instruction>();
        testSimpleRepeat(instructionList, 100, 1);
    }

    @Test
    public void testSingleRepeatRepeatsTenTimes() throws NotEnoughInstructionsToRepeatException {
        List<Instruction> instructionList = new ArrayList<Instruction>();
        testSimpleRepeat(instructionList, 100, 10);
    }

    @Test
    public void testMultipleRepeatRepeatsOnce() throws NotEnoughInstructionsToRepeatException {
        List<Instruction> instructionList = new ArrayList<Instruction>();
        testMultipleRepeat(instructionList, 5, 1);
    }

    @Test
    public void testMultipleRepeatRepeatsFiveTimes() throws NotEnoughInstructionsToRepeatException {
        List<Instruction> instructionList = new ArrayList<Instruction>();
        testMultipleRepeat(instructionList, 5, 5);
    }

    private void testMultipleRepeat(List<Instruction> instructionList, int repeatCount, int instructionCount) throws NotEnoughInstructionsToRepeatException {
        for (int loop = 0; loop < repeatCount; loop++) {
            instructionList.add(getDelayInstruction(loop));
        }

        instructionList.add(repeatInstructionFactory.create(repeatCount, instructionCount));

        instructionList = repeatInstructionListProcessor.process(instructionList);

        int totalInstructionCount = repeatCount + (repeatCount * instructionCount);
        Assert.assertThat(totalInstructionCount, is(instructionList.size()));

        for (int loop = repeatCount; loop < totalInstructionCount; loop++) {
            Instruction instruction = instructionList.get(loop);
            Assert.assertThat(instruction, instanceOf(DelayInstruction.class));

            DelayInstruction delayInstruction = (DelayInstruction) instruction;
            DelayInstruction repeatedDelayInstruction = (DelayInstruction) instructionList.get(loop - instructionCount);
            Assert.assertThat(delayInstruction.getDelayValue(), is(repeatedDelayInstruction.getDelayValue()));
        }
    }

    private void testSimpleRepeat(List<Instruction> instructionList, int delayInMilliseconds, int repeatCount) throws NotEnoughInstructionsToRepeatException {
        instructionList.add(getDelayInstruction(delayInMilliseconds));
        instructionList.add(repeatInstructionFactory.create(repeatCount, 1));

        instructionList = repeatInstructionListProcessor.process(instructionList);

        int totalInstructionCount = repeatCount + 1;
        Assert.assertThat(totalInstructionCount, is(instructionList.size()));

        for (int loop = 0; loop < totalInstructionCount; loop++) {
            Instruction instruction = instructionList.get(loop);
            Assert.assertThat(instruction, instanceOf(DelayInstruction.class));

            DelayInstruction delayInstruction = (DelayInstruction) instruction;
            Assert.assertThat(delayInMilliseconds, is(delayInstruction.getDelayValue()));
        }
    }
}
