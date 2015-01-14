package com.timmattison.hacking.usbrubberducky;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.timmattison.hacking.usbrubberducky.instructions.*;
import com.timmattison.hacking.usbrubberducky.instructions.factories.*;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.SimulateTypingInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.BasicInstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionListFactory;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.*;
import com.timmattison.hacking.usbrubberducky.parsers.InstructionParser;
import com.timmattison.hacking.usbrubberducky.parsers.advanced.KeypressInstructionParser;
import com.timmattison.hacking.usbrubberducky.parsers.regex.*;
import com.timmattison.hacking.usbrubberducky.preprocessors.LegacyPreprocessor;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;
import com.timmattison.hacking.usbrubberducky.support.BitCounter;
import com.timmattison.hacking.usbrubberducky.support.ParallelBitCounter;
import com.timmattison.hacking.usbrubberducky.translation.BasicCharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.BasicKeyboardModifierMap;
import com.timmattison.hacking.usbrubberducky.translation.CharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.codes.KeyboardModifierMap;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.KeyboardCodes;
import com.timmattison.hacking.usbrubberducky.translation.keyboards.USKeyboardCodes;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 10/25/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class UsbRubberDuckyModule extends AbstractModule {
    @Override
    protected void configure() {
        // Create a multi-binder that holds all of the instruction parsers
        createInstructionParsingMultibinder();

        // Create a multi-binder that does instruction list processing
        createListProcessingMultibinder();

        // Bind the concrete implementations
        bindConcreteImplementations();

        // Create factories from the factory interfaces
        createFactoriesFromInterfaces();
    }

    private void createInstructionParsingMultibinder() {
        Multibinder<InstructionParser> instructionParserMultibinder = Multibinder.newSetBinder(binder(), InstructionParser.class);

        // Support "STRING"
        instructionParserMultibinder.addBinding().to(StringInstructionParser.class);
        // Support "DELAY"
        instructionParserMultibinder.addBinding().to(DelayInstructionParser.class);
        // Support keypresses
        instructionParserMultibinder.addBinding().to(KeypressInstructionParser.class);
        // Support "REPEAT"
        instructionParserMultibinder.addBinding().to(RepeatInstructionParser.class);
        // Support "REPEAT" with multiple instructions
        instructionParserMultibinder.addBinding().to(MultipleRepeatInstructionParser.class);
        // Support "REM"
        instructionParserMultibinder.addBinding().to(RemInstructionParser.class);
        // Support "DEFAULT_DELAY"
        instructionParserMultibinder.addBinding().to(DefaultDelayInstructionParser.class);
        // Support blank lines
        instructionParserMultibinder.addBinding().to(BlankLineInstructionParser.class);
        // Support "SIMULATE_TYPING"
        instructionParserMultibinder.addBinding().to(SimulateTypingInstructionParser.class);
    }

    private void createListProcessingMultibinder() {
        Multibinder<InstructionListProcessor> instructionListProcessorMultibinder = Multibinder.newSetBinder(binder(), InstructionListProcessor.class);

        // NOTE: The order of this list is important!

        // Step 1 - Prune NOP instructions
        instructionListProcessorMultibinder.addBinding().to(NopInstructionListProcessor.class);
        // Step 2 - Repeat instructions
        instructionListProcessorMultibinder.addBinding().to(RepeatInstructionListProcessor.class);
        // Step 3 - Add default delays
        instructionListProcessorMultibinder.addBinding().to(DefaultDelayInstructionListProcessor.class);
        // Step 4 - Add simulated typing
        instructionListProcessorMultibinder.addBinding().to(SimulateTypingInstructionListProcessor.class);
    }

    private void bindConcreteImplementations() {
        // TODO: Add more keyboards and make it configurable at runtime

        // Use the US keyboard
        bind(KeyboardCodes.class).to(USKeyboardCodes.class);

        // Use the basic character translator
        bind(CharacterTranslator.class).to(BasicCharacterTranslator.class);

        // Use the pre-processor that cleans up some legacy instructions
        bind(Preprocessor.class).to(LegacyPreprocessor.class);

        // Use the parallel bit counter
        bind(BitCounter.class).to(ParallelBitCounter.class);

        // Use the standard rubber ducky encoder
        bind(RubberDuckyEncoder.class).to(StandardRubberDuckyEncoder.class);

        // Use the basic keyboard modifier map
        bind(KeyboardModifierMap.class).to(BasicKeyboardModifierMap.class);
    }

    private void createFactoriesFromInterfaces() {
        install(new FactoryModuleBuilder().implement(StringInstruction.class, StringInstruction.class).build(StringInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(RepeatInstruction.class, BasicRepeatInstruction.class).build(RepeatInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(KeypressInstruction.class, KeypressInstruction.class).build(KeypressInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(DelayInstruction.class, DelayInstruction.class).build(DelayInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(SimulateTypingInstruction.class, BasicSimulateTypingInstruction.class).build(SimulateTypingInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(InstructionList.class, BasicInstructionList.class).build(InstructionListFactory.class));
    }
}
