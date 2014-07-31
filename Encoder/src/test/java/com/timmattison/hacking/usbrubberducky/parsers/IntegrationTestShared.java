package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.BasicInstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.InstructionListProcessor;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by timmattison on 7/31/14.
 */
public class IntegrationTestShared {
    public static void testFile(Injector injector, String inputPath, String inputSuffix, String filename) throws Exception {
        // Get the instruction list processors
        Set<InstructionListProcessor> instructionListProcessors = getInstructionListProcessors(injector);

        // Get the instruction list
        InstructionList instructionList = new BasicInstructionList(instructionListProcessors);

        // Test the file against the legacy encoder
        testAgainstLegacyEncoder(injector, instructionList, inputPath, inputSuffix, filename);
    }

    private static Set<InstructionListProcessor> getInstructionListProcessors(Injector injector) {
        // Create a type literal implementation that is for Set<InstructionListProcessor>
        TypeLiteral<Set<InstructionListProcessor>> instructionListProcessorTypeLiteral = new TypeLiteral<Set<InstructionListProcessor>>() {
        };

        // Get the multibinding from Guice for Set<InstructionListProcessor>
        return injector.getInstance(Key.get(instructionListProcessorTypeLiteral));
    }

    private static void testAgainstLegacyEncoder(Injector injector, InstructionList instructionList, String inputPath, String inputSuffix, String filename) throws Exception {
        String[] inputFile;
        byte[] outputFile;

        // Get the input file data
        try {
            inputFile = getInputFile(inputPath, inputSuffix, filename);
        } catch (NullPointerException e) {
            throw new UnsupportedOperationException("Input file [" + filename + ".txt] not found");
        }

        // Get the output file from the legacy encoder
        outputFile = getOutputFileFromLegacyEncoder(inputPath, inputSuffix, filename);

        // Get the output from the current encoder
        byte[] generatedData = getOutputFromCurrentEncoder(injector, instructionList, inputFile);

        // Make sure the output matches
        Assert.assertArrayEquals("There was an issue with " + filename, outputFile, generatedData);
    }

    private static byte[] getOutputFromCurrentEncoder(Injector injector, InstructionList instructionList, String[] inputFile) throws IOException {
        // Get the set of instruction parsers
        Set<InstructionParser> instructionParserSet = getInstructionParserSet(injector);

        // Loop through each input line
        for (String line : inputFile) {
            // Start out with a null instruction
            Instruction instruction = null;

            // Loop through each parser
            for (InstructionParser instructionParser : instructionParserSet) {
                // Run the parser on this line
                instruction = instructionParser.parse(line);

                // Was the parser successful?
                if (instruction != null) {
                    // Yes, exit the for loop
                    break;
                }
            }

            // Did we find a parser that could handle this line?
            if (instruction == null) {
                // No, throw an exception
                throw new UnsupportedOperationException("Couldn't process instruction [" + line + "]");
            }

            // Add the instruction to the list
            instructionList.addInstruction(instruction);
        }

        // Get the byte array from this instruction list
        return instructionList.getBytes();
    }

    /**
     * Gets the set of instruction parser from Guice
     *
     * @return
     */
    private static Set<InstructionParser> getInstructionParserSet(Injector injector) {
        // Create a type literal implementation that is for Set<InstructionParser>
        TypeLiteral<Set<InstructionParser>> instructionParserTypeLiteral = new TypeLiteral<Set<InstructionParser>>() {
        };

        // Get the multibinding from Guice for Set<InstructionParser>
        return injector.getInstance(Key.get(instructionParserTypeLiteral));
    }

    private static byte[] getOutputFileFromLegacyEncoder(String inputPath, String inputSuffix, String filename) throws Exception {
        byte[] outputFile;

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);

        // Save the original System.out
        PrintStream old = System.out;

        // Use the printStream
        System.setOut(printStream);

        // Build the arguments for the encoder
        List<String> args = buildEncoderArgs(inputPath, inputSuffix, filename);

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

    private static List<String> buildEncoderArgs(String inputPath, String inputSuffix, String filename) {
        List<String> args = new ArrayList<String>();

        // Input file arguments
        args.add("-i");
        args.add(getInputFileName(inputPath, inputSuffix, filename));

        // Output file arguments
        args.add("-o");
        args.add("-");

        // Test mode argument
        args.add("-t");

        return args;
    }

    public static String[] getInputFile(String inputPath, String inputSuffix, String filename) throws IOException {
        return readFileAsStringArray(inputPath + filename + inputSuffix);
    }

    public static String getInputFileName(String inputPath, String inputSuffix, String filename) {
        return inputPath + filename + inputSuffix;
    }

    private static String[] readFileAsStringArray(String inputFile) throws IOException {
        String string = IOUtils.toString(new InputStreamReader(inputFile.getClass().getResourceAsStream(inputFile)));
        string = string.replaceAll("\r", "");
        String[] lines = string.split("\n");

        return lines;
    }
}
