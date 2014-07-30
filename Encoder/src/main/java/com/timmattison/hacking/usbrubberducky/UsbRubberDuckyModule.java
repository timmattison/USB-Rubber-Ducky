package com.timmattison.hacking.usbrubberducky;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.timmattison.hacking.usbrubberducky.instructions.BasicRepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.DelayInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.KeypressInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.DelayInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.interfaces.RepeatInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.StringInstruction;
import com.timmattison.hacking.usbrubberducky.instructions.factories.KeypressInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.RepeatInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.factories.StringInstructionFactory;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.DefaultDelayInstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.InstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.NopInstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.RepeatInstructionListProcessor;
import com.timmattison.hacking.usbrubberducky.parsers.*;
import com.timmattison.hacking.usbrubberducky.translation.BasicCharacterTranslator;
import com.timmattison.hacking.usbrubberducky.translation.CharacterTranslator;
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
        Multibinder<InstructionParser> instructionParserMultibinder = Multibinder.newSetBinder(binder(), InstructionParser.class);

        instructionParserMultibinder.addBinding().to(StringInstructionParser.class);
        instructionParserMultibinder.addBinding().to(DelayInstructionParser.class);
        instructionParserMultibinder.addBinding().to(KeypressInstructionParser.class);
        instructionParserMultibinder.addBinding().to(RepeatInstructionParser.class);
        instructionParserMultibinder.addBinding().to(NopInstructionParser.class);
        instructionParserMultibinder.addBinding().to(DefaultDelayInstructionParser.class);
        instructionParserMultibinder.addBinding().to(BlankLineInstructionParser.class);

        Multibinder<InstructionListProcessor> instructionListProcessorMultibinder = Multibinder.newSetBinder(binder(), InstructionListProcessor.class);

        instructionListProcessorMultibinder.addBinding().to(NopInstructionListProcessor.class);
        instructionListProcessorMultibinder.addBinding().to(RepeatInstructionListProcessor.class);
        instructionListProcessorMultibinder.addBinding().to(DefaultDelayInstructionListProcessor.class);

        bind(KeyboardCodes.class).to(USKeyboardCodes.class);
        bind(CharacterTranslator.class).to(BasicCharacterTranslator.class);

        install(new FactoryModuleBuilder().implement(StringInstruction.class, StringInstruction.class).build(StringInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(RepeatInstruction.class, BasicRepeatInstruction.class).build(RepeatInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(KeypressInstruction.class, KeypressInstruction.class).build(KeypressInstructionFactory.class));
        install(new FactoryModuleBuilder().implement(DelayInstruction.class, DelayInstruction.class).build(DelayInstructionFactory.class));
    }
}
