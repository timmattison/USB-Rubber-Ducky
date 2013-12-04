package com.timmattison.hacking.usbrubberducky.instructions;

import com.google.inject.assistedinject.Assisted;
import com.timmattison.hacking.usbrubberducky.translation.CharacterTranslator;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 12/3/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringInstruction implements Instruction {
    private final CharacterTranslator characterTranslator;
    private final String input;

    @Inject
    public StringInstruction(CharacterTranslator characterTranslator, @Assisted("input") String input) {
        this.characterTranslator = characterTranslator;
        this.input = input;
    }

    @Override
    public byte[] getEncodedInstruction() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int loop = 0; loop < input.length(); loop++) {
            characterTranslator.translate(input.charAt(loop)).write(baos);
        }

        return baos.toByteArray();
    }
}
