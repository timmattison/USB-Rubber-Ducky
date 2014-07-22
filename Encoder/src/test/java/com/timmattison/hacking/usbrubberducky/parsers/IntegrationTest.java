package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.timmattison.hacking.usbrubberducky.UsbRubberDuckyModule;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

/**
 * Created by timmattison on 7/21/14.
 */
public class IntegrationTest {
    Injector injector;

    @Before
    public void setup() {
        injector = Guice.createInjector(new UsbRubberDuckyModule());
    }

    @Test
    public void testHelloWorld() throws IOException {
        String filename = "hello-world";

        testFile(filename);
    }

    @Test
    public void testDownloadAndExecute() throws IOException {
        String filename = "download-and-execute";

        testFile(filename);
    }

    private void testFile(String filename) throws IOException {
        String[] inputFile = TestAgainstFiles.getInputFile(filename);
        byte[] outputFile = TestAgainstFiles.getOutputFile(filename);

        TypeLiteral<Set<InstructionParser>> instructionParserTypeLiteral = new TypeLiteral<Set<InstructionParser>>(){};
        Set<InstructionParser> instructionParserSet = injector.getInstance(Key.get(instructionParserTypeLiteral));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for(String line : inputFile) {
            // Trim leading and trailing whitespace
            line = line.trim();

            Instruction instruction = null;

            for(InstructionParser instructionParser : instructionParserSet) {
                instruction = instructionParser.parse(line);

                if(instruction != null) {
                    break;
                }
            }

            if(instruction == null) {
                // Couldn't process this instruction!
                throw new UnsupportedOperationException("Couldn't process instruction [" + line + "]");
            }

            byte[] encodedInstruction = instruction.getEncodedInstruction();
            baos.write(encodedInstruction);
        }

        byte[] generatedOutput = baos.toByteArray();

        Assert.assertArrayEquals(outputFile, generatedOutput);
    }
}
