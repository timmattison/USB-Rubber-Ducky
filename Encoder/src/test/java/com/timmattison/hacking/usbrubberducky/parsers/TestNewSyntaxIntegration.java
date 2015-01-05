package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.timmattison.hacking.usbrubberducky.UsbRubberDuckyModule;
import org.junit.Before;
import org.junit.Test;

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
}

