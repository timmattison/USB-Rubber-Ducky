package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.timmattison.hacking.usbrubberducky.UsbRubberDuckyModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by timmattison on 7/31/14.
 */
public class OsxIntegrationTests {
    private static final String inputPath = "/inputs/osx/";
    private static final String inputSuffix = ".txt";

    private static final byte[] osxKillFinderBinary = {(byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xc3, (byte) 0x29, (byte) 0x0c, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xf5, (byte) 0x09, (byte) 0x00, (byte) 0x0c, (byte) 0x00, (byte) 0x11, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x15, (byte) 0x00, (byte) 0x00, (byte) 0x64, (byte) 0x28, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0x00, (byte) 0xf5, (byte) 0x28, (byte) 0x00};

    Injector injector;

    @Before
    public void setup() {
        injector = Guice.createInjector(new UsbRubberDuckyModule());
    }

    @Test
    public void testOsxKillFinder() throws IOException {
        String filename = "osx-kill-finder";

        byte[] outputData = IntegrationTestShared.getOutputFromCurrentEncoder(injector, inputPath, inputSuffix, filename);
        Assert.assertArrayEquals(osxKillFinderBinary, outputData);
    }

}
