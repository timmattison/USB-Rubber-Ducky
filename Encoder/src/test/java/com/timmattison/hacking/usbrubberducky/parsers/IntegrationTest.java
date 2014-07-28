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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Test
    public void testPayloadNetcatFtpDownloadAndReverseShell() throws IOException {
        String filename = "netcat-ftp-download-and-reverse-shell";

        testFile(filename);
    }

    @Test
    public void testWallpaperPrank() throws IOException {
        String filename = "wallpaper-prank";

        testFile(filename);
    }

    @Test
    public void testHideCmdWindow() throws IOException {
        String filename = "hide-cmd-window-expanded";

        testFile(filename);
    }

    @Test
    public void testYouGotQuacked() throws IOException {
        String filename = "you-got-quacked";

        testFile(filename);
    }

    private void testFile(String filename) throws IOException {
        String[] inputFile = TestAgainstFiles.getInputFile(filename);
        byte[] outputFile = TestAgainstFiles.getOutputFile(filename);

        TypeLiteral<Set<InstructionParser>> instructionParserTypeLiteral = new TypeLiteral<Set<InstructionParser>>() {
        };
        Set<InstructionParser> instructionParserSet = injector.getInstance(Key.get(instructionParserTypeLiteral));

        List<Instruction> instructions = new ArrayList<Instruction>();

        for (String line : inputFile) {
            // Trim leading and trailing whitespace
            line = line.trim();

            Instruction instruction = null;

            for (InstructionParser instructionParser : instructionParserSet) {
                instruction = instructionParser.parse(line);

                if (instruction != null) {
                    break;
                }
            }

            if (instruction == null) {
                // Couldn't process this instruction!
                throw new UnsupportedOperationException("Couldn't process instruction [" + line + "]");
            }

            instructions.add(instruction);
        }

        int counter = 0;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (Instruction instruction : instructions) {
            byte[] encodedInstruction = instruction.getEncodedInstruction();
            baos.write(encodedInstruction);

            byte[] instructionToTestAgainst = Arrays.copyOfRange(outputFile, counter, counter + encodedInstruction.length);

            if(!Arrays.equals(instructionToTestAgainst, encodedInstruction)) {
                // Bad!
                if((instructionToTestAgainst[1] == 1) && (encodedInstruction[1] == 3)) {
                    System.out.println("Could be a case issue");
                }
                else {
                    System.out.println("BAD");
                }
            }

            Assert.assertArrayEquals(instruction.toString() + " failed to match", instructionToTestAgainst, encodedInstruction);

            counter += encodedInstruction.length;
        }

        byte[] generatedOutput = baos.toByteArray();

        Assert.assertArrayEquals(outputFile, generatedOutput);
    }
}
