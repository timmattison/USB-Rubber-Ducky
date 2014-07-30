package com.timmattison.hacking.usbrubberducky;

import com.google.inject.Guice;
import com.google.inject.Injector;
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
    public static void main(String args[]) {
        Injector injector = Guice.createInjector(new UsbRubberDuckyModule());

        // Do we have the right number of arguments?
        if ((args == null) || (args.length != 2)) {
            // No, print out the usage info and exit
            showUsageInfo();
        }

        String inputFile = args[0];
        String outputFile = args[1];

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
            // There was an exception, report it and exit
            reportEncoderIoException(inputFile, e);
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
