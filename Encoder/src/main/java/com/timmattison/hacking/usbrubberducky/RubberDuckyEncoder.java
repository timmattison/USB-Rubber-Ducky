package com.timmattison.hacking.usbrubberducky;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.timmattison.hacking.usbrubberducky.exceptions.NoParserFoundForStringException;
import com.timmattison.hacking.usbrubberducky.instructions.BasicRepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.KeypressInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.StringInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.DelayInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.KeypressInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.RepeatInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.StringInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.DefaultDelayInstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.InstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.NopInstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.RepeatInstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.parsers.InstructionParser;
import com.timmattison.hacking.usbrubberducky.parsers.advanced.KeypressInstructionParser;
import com.timmattison.hacking.usbrubberducky.parsers.regex.*;
import com.timmattison.hacking.usbrubberducky.preprocessors.LegacyPreprocessor;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;
import com.timmattison.hacking.usbrubberducky.support.BitCounter;
import com.timmattison.hacking.usbrubberducky.support.ParallelBitCounter;
import com.timmattison.hacking.usbrubberducky.translation.BasicCharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.CharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.USKeyboardCodes;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 10/25/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RubberDuckyEncoder {
    public byte[] encode(String[] input) throws IOException, NoParserFoundForStringException;
}
