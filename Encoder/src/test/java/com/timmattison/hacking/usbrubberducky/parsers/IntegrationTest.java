package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.timmattison.hacking.usbrubberducky.UsbRubberDuckyModule;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.BasicInstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
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

    @Test
    public void testReverseShell() throws IOException {
        String filename = "reverse-shell";

        testFile(filename);
    }

    @Test
    public void testForkBomb() throws IOException {
        String filename = "fork-bomb";

        testFile(filename);
    }

    @Test
    public void testUtilmanExploit() throws IOException {
        String filename = "utilman-exploit";

        testFile(filename);
    }

    @Test
    public void testWifiBackdoor() throws IOException {
        String filename = "wifi-backdoor";

        testFile(filename);
    }

    @Test
    public void testNonMaliciousAutoDefacer() throws IOException {
        String filename = "non-malicious-auto-defacer";

        testFile(filename);
    }

    @Test
    public void testLockYourComputerMessage() throws IOException {
        String filename = "lock-your-computer-message";

        testFile(filename);
    }

    @Test
    public void testDuckyDownloader() throws IOException {
        String filename = "ducky-downloader";

        testFile(filename);
    }

    @Test
    public void testDuckyPhisher() throws IOException {
        String filename = "ducky-phisher";

        testFile(filename);
    }

    private void testFile(String filename) throws IOException {
        String[] inputFile;
        byte[] outputFile;

        try {
            inputFile = TestAgainstFiles.getInputFile(filename);
        } catch (NullPointerException e) {
            throw new UnsupportedOperationException("Input file [" + filename + ".txt] not found");
        }

        outputFile = getOutputFileFromOriginalEncoder(filename);

        TypeLiteral<Set<InstructionParser>> instructionParserTypeLiteral = new TypeLiteral<Set<InstructionParser>>() {
        };
        Set<InstructionParser> instructionParserSet = injector.getInstance(Key.get(instructionParserTypeLiteral));

        BasicInstructionList basicInstructionList = new BasicInstructionList();

        for (String line : inputFile) {
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

            basicInstructionList.addInstruction(instruction);
        }

        byte[] generatedData = basicInstructionList.getBytes();

        Assert.assertArrayEquals("There was an issue with " + filename, outputFile, generatedData);
    }

    private byte[] getOutputFileFromOriginalEncoder(String filename) throws IOException {
        byte[] outputFile;// Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);

        // Save the original System.out
        PrintStream old = System.out;

        // Use the printStream
        System.setOut(printStream);

        // Build the arguments for the encoder
        List<String> args = buildEncoderArgs(filename);

        // Run the encoder and capture the output
        com.hak5.usbrubberducky.Encoder.main(args.toArray(new String[args.size()]));

        // Flush the output
        System.out.flush();

        // Go back to the original System.out
        System.setOut(old);

        // Get the output from the Encoder
        outputFile = baos.toByteArray();
        return outputFile;
    }

    private List<String> buildEncoderArgs(String filename) {
        List<String> args = new ArrayList<String>();

        // Input file arguments
        args.add("-i");
        args.add(TestAgainstFiles.getInputFileName(filename));

        // Output file arguments
        args.add("-o");
        args.add("-");

        // Test mode argument
        args.add("-t");

        return args;
    }
}
