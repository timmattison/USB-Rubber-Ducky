package com.timmattison.hacking.usbrubberducky;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.timmattison.hacking.usbrubberducky.exceptions.EncoderException;
import com.timmattison.hacking.usbrubberducky.exceptions.NoParserFoundForStringException;
import org.apache.commons.cli.*;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 10/25/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class RubberDuckyEncoderApplication {
    public static final String INPUT = "input";
    public static final String OUTPUT = "output";
    public static final String INPUT_FILE_NAME = "input file name";
    public static final String OUTPUT_FILE_NAME = "output file name";
    public static final String ENCODER_APPLICATION_NAME = "Encoder";
    private static String inputFile = null;
    private static String outputFile = null;

    public static void main(String args[]) throws ParseException, EncoderException, IOException {
        // Print the version number
        printVersionNumber();

        // Process the command-line arguments with Apache CLI
        processCommandLineOptions(args);

        Injector injector = Guice.createInjector(new UsbRubberDuckyModule());

        // Read the input file
        String[] input = readInputFile(inputFile);

        // Could we read it?
        if (input == null) {
            // No, tell the user that we couldn't read it for some reason and exit
            showInputReadFailure(inputFile);
        }

        RubberDuckyEncoder rubberDuckyEncoder = injector.getInstance(RubberDuckyEncoder.class);

        byte[] output = null;

        try {
            output = rubberDuckyEncoder.encode(input);
        } catch (IOException e) {
            // There was an IO exception, report it and exit
            reportEncoderIoException(inputFile, e);
        } catch (EncoderException e) {
            // There was an encoder exception, report it and exit
            reportEncoderException(e);
        }

        try {
            File finalOutputFile = new File(outputFile);
            FileOutputStream fileOutputStream = new FileOutputStream(finalOutputFile);
            fileOutputStream.write(output);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            // There was an exception, report it and exit
            reportOutputIoException(inputFile, e);
        }
    }

    private static void printVersionNumber() throws IOException {
        String gitHash = RubberDuckyEncoderApplication.class.getPackage().getImplementationVersion();

        if (gitHash == null) {
            gitHash = "Unknown";
        }

        System.out.println("Rubber Ducky Encoder Application - githash: " + gitHash);
    }

    private static void processCommandLineOptions(String[] args) throws ParseException {
        // Create the Options object
        Options options = new Options();

        // Add input and output options
        options.addOption(INPUT, true, INPUT_FILE_NAME);
        options.addOption(OUTPUT, true, OUTPUT_FILE_NAME);

        CommandLineParser commandLineParser = new BasicParser();
        CommandLine commandLine = commandLineParser.parse(options, args);

        inputFile = commandLine.getOptionValue(INPUT);
        outputFile = commandLine.getOptionValue(OUTPUT);

        if ((inputFile == null) || (outputFile == null)) {
            // Automatically generate the usage info with Apache CLI
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(ENCODER_APPLICATION_NAME, options);
            System.exit(1);
        }
    }

    private static void reportOutputIoException(String outputFile, IOException e) {
        System.err.println("Couldn't save the binary to the output file [" + outputFile + "].  Check to make sure you have permission to write to it.");
        e.printStackTrace();
        System.exit(4);
    }

    private static void reportEncoderIoException(String inputFile, Exception e) {
        System.err.println("Couldn't encode the the input file [" + inputFile + "].  Check to make sure the syntax is correct.");
        e.printStackTrace();
        System.exit(3);
    }

    private static void reportEncoderException(Exception e) {
        if (e instanceof NoParserFoundForStringException) {
            NoParserFoundForStringException noParserFoundForStringException = (NoParserFoundForStringException) e;

            String badInput = noParserFoundForStringException.getString();

            if (badInput == null) {
                System.err.println("An unknown line caused the exception, this should never happen");
            } else {
                System.err.println("This line could not be parsed: " + badInput);
            }

            System.err.println();
        }

        e.printStackTrace();
        System.exit(4);
    }

    private static void showInputReadFailure(String inputFile) {
        System.err.println("Couldn't read the input file [" + inputFile + "].  Check to make sure the file exists and that you have permission to access it.");
        System.exit(2);
    }

    private static String[] readInputFile(String inputFile) {
        try {
            String string = IOUtils.toString(new InputStreamReader(new FileInputStream(inputFile)));
            string = string.replaceAll("\r", "");
            String[] lines = string.split("\n");

            return lines;
        } catch (IOException e) {
            return null;
        }
    }

    private static void showUsageInfo() {
        System.err.println("You must specify an input file as the first argument and the output file as the second argument.");
        System.exit(1);
    }
}
