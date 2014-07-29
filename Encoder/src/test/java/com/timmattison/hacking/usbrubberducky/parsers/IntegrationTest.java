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
    public void testHelloWorld() throws Exception {
        String filename = "hello-world";

        testFile(filename);
    }

    @Test
    public void testDownloadAndExecute() throws Exception {
        String filename = "download-and-execute";

        testFile(filename);
    }

    @Test
    public void testPayloadNetcatFtpDownloadAndReverseShell() throws Exception {
        String filename = "netcat-ftp-download-and-reverse-shell";

        testFile(filename);
    }

    @Test
    public void testWallpaperPrank() throws Exception {
        String filename = "wallpaper-prank";

        testFile(filename);
    }

    @Test
    public void testHideCmdWindow() throws Exception {
        String filename = "hide-cmd-window-expanded";

        testFile(filename);
    }

    @Test
    public void testYouGotQuacked() throws Exception {
        String filename = "you-got-quacked";

        testFile(filename);
    }

    @Test
    public void testReverseShell() throws Exception {
        String filename = "reverse-shell";

        testFile(filename);
    }

    @Test
    public void testForkBomb() throws Exception {
        String filename = "fork-bomb";

        testFile(filename);
    }

    @Test
    public void testUtilmanExploit() throws Exception {
        String filename = "utilman-exploit";

        testFile(filename);
    }

    @Test
    public void testWifiBackdoor() throws Exception {
        String filename = "wifi-backdoor";

        testFile(filename);
    }

    @Test
    public void testNonMaliciousAutoDefacer() throws Exception {
        String filename = "non-malicious-auto-defacer";

        testFile(filename);
    }

    @Test
    public void testLockYourComputerMessage() throws Exception {
        String filename = "lock-your-computer-message";

        testFile(filename);
    }

    @Test
    public void testDuckyDownloader() throws Exception {
        String filename = "ducky-downloader";

        testFile(filename);
    }

    @Test
    public void testDuckyPhisher() throws Exception {
        String filename = "ducky-phisher";

        testFile(filename);
    }

    @Test
    public void testFtpUploadDownload() throws Exception {
        String filename = "ftp-upload-download";

        testFile(filename);
    }

    @Test
    public void testRestartPrank() throws Exception {
        String filename = "restart-prank";

        testFile(filename);
    }

    @Test
    public void testSillyMouseWindowsIsForKids() throws Exception {
        String filename = "silly-mouse-windows-is-for-kids";

        testFile(filename);
    }

    @Test
    public void testWindowsScreenRotationHack() throws Exception {
        String filename = "windows-screen-rotation-hack";

        testFile(filename);
    }

    @Test
    public void testPowershellWgetAndExecute() throws Exception {
        String filename = "powershell-wget-and-execute";

        testFile(filename);
    }

    @Test
    public void testPowershellWgetAndExecuteHidden() throws Exception {
        String filename = "powershell-wget-and-execute-hidden";

        testFile(filename);
    }

    @Test
    public void testMobileTabs() throws Exception {
        String filename = "mobile-tabs";

        testFile(filename);
    }

    @Test
    public void testCreateWirelessNetworkAssociation() throws Exception {
        String filename = "create-wireless-network-association";

        testFile(filename);
    }

    @Test
    public void testRetrieveSamAndSystemFromALiveFileSystem() throws Exception {
        String filename = "retrieve-sam-and-system-from-a-live-file-system";

        testFile(filename);
    }

    @Test
    public void testRetrieveSamAndSystemFromALiveFileSystem2() throws Exception {
        String filename = "retrieve-sam-and-system-from-a-live-file-system2";

        testFile(filename);
    }

    @Test
    public void testUglyRolledPrank() throws Exception {
        String filename = "ugly-rolled-prank";

        testFile(filename);
    }

    @Test
    public void testXmas() throws Exception {
        String filename = "xmas";

        testFile(filename);
    }

    @Test
    public void testPineappleAssociationVeryFast() throws Exception {
        String filename = "pineapple-association-very-fast";

        testFile(filename);
    }

    @Test
    public void testOsxRootBackdoor() throws Exception {
        String filename = "osx-root-backdoor";

        testFile(filename);
    }

    @Test
    public void testOsxLocalDnsPoisoning() throws Exception {
        String filename = "osx-local-dns-poisoning";

        testFile(filename);
    }

    private void testFile(String filename) throws Exception {
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

        InstructionList basicInstructionList = new BasicInstructionList();

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

    private byte[] getOutputFileFromOriginalEncoder(String filename) throws Exception {
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

    // The tests below blow up the original encoder

// FAILS: Original encoder doesn't support CTRL-SHIFT ENTER or CTRL SHIFT ENTER
//    @Test
//    public void testMimikatz1() throws Exception {
//        String filename = "mimikatz-1";
//
//        testFile(filename);
//    }

// FAILS: Original encoder crashes on blank lines
//    @Test
//    public void testMimikatz2() throws Exception {
//        String filename = "mimikatz-2";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support CTRL-SHIFT ENTER or CTRL SHIFT ENTER
//    @Test
//    public void testWifunV1_1() throws Exception {
//        String filename = "wifun-v1-1";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support CTRL-SHIFT ENTER or CTRL SHIFT ENTER
//    @Test
//    public void testMissDirection() throws Exception {
//        String filename = "miss-direction";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support CTRL-SHIFT ENTER or CTRL SHIFT ENTER
//    @Test
//    public void testRemotelyPossible() throws Exception {
//        String filename = "remotely-possible";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support REPEAT
//    @Test
//    public void testBatchWiperDriveEraser() throws Exception {
//        String filename = "batch-wiper-drive-eraser";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support REPEAT
//    @Test
//    public void testGenericBatch() throws Exception {
//        String filename = "generic-batch";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support REPEAT
//    @Test
//    public void testPaintHack() throws Exception {
//        String filename = "paint-hack";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support REPEAT
//    @Test
//    public void testLocalDnsPoisoning() throws Exception {
//        String filename = "local-dns-poisoning";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support REPEAT
//    @Test
//    public void testDenyNetAccess() throws Exception {
//        String filename = "deny-net-access";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support REPEAT
//    @Test
//    public void testRunexeFromSd() throws Exception {
//        String filename = "runexe-from-sd";
//
//        testFile(filename);
//    }

// FAILS: Original encoder doesn't support REPEAT
//    @Test
//    public void testRunJavaFromSd() throws Exception {
//        String filename = "run-java-from-sd";
//
//        testFile(filename);
//    }

// FAILS: Original encoder encodes "GUI SPACE" as "GUI S"
//    @Test
//    public void testOsxUserBackdoor() throws Exception {
//        String filename = "osx-user-backdoor";
//
//        testFile(filename);
//    }

// FAILS: Original encoder encodes "GUI SPACE" as "GUI S"
//    @Test
//    public void testYoutubeBlaster() throws Exception {
//        String filename = "osx-youtube-blaster";
//
//        testFile(filename);
//    }

// FAILS: Original encoder encodes "GUI SPACE" as "GUI S"
//    @Test
//    public void testOsxAsciiPrank() throws Exception {
//        String filename = "osx-ascii-prank";
//
//        testFile(filename);
//    }

}

