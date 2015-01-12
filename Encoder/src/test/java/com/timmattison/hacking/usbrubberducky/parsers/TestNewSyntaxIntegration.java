package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.timmattison.hacking.usbrubberducky.UsbRubberDuckyModule;
import com.timmattison.hacking.usbrubberducky.exceptions.EncoderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by timmattison on 7/21/14.
 */
public class TestNewSyntaxIntegration {
    private static final String inputPath = "/inputs/new-syntax/";
    private static final String inputSuffix = ".txt";

    Injector injector;

    @Before
    public void setup() {
        injector = Guice.createInjector(new UsbRubberDuckyModule());
    }

    @Test
    public void testMultipleRepeat1() throws Exception {
        String filename = "multiple-repeat-1";

        IntegrationTestShared.testNewFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testMultipleRepeat2() throws IOException, EncoderException {
        String[] input1 = new String[]{"STRING blah", "REPEAT 1 1"};
        String[] input2 = new String[]{"STRING blah", "REPEAT 1"};
        String[] input3 = new String[]{"STRING blah", "STRING blah"};

        // Get the output from the current encoder
        byte[] output1 = IntegrationTestShared.getOutputFromCurrentEncoder(injector, input1);
        byte[] output2 = IntegrationTestShared.getOutputFromCurrentEncoder(injector, input2);
        byte[] output3 = IntegrationTestShared.getOutputFromCurrentEncoder(injector, input3);

        Assert.assertArrayEquals(output3, output1);
        Assert.assertArrayEquals(output3, output2);
    }
}
