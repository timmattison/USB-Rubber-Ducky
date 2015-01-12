package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.timmattison.hacking.usbrubberducky.UsbRubberDuckyModule;
import com.timmattison.hacking.usbrubberducky.exceptions.EncoderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

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
        String[] newRepeatStyleInput = new String[]{"STRING blah", "REPEAT 1 1"};
        String[] oldRepeatStyleInput = new String[]{"STRING blah", "REPEAT 1"};
        String[] expectedInput = new String[]{"STRING blah", "STRING blah"};

        // Make sure the inputs are different
        Assert.assertThat(newRepeatStyleInput, not(equalTo(oldRepeatStyleInput)));
        Assert.assertThat(newRepeatStyleInput, not(equalTo(expectedInput)));
        Assert.assertThat(oldRepeatStyleInput, not(equalTo(expectedInput)));

        // Get the output from the current encoder
        byte[] newRepeatStyleOutput = IntegrationTestShared.getOutputFromCurrentEncoder(injector, newRepeatStyleInput);
        byte[] oldRepeatStyleOutput = IntegrationTestShared.getOutputFromCurrentEncoder(injector, oldRepeatStyleInput);
        byte[] expectedOutput = IntegrationTestShared.getOutputFromCurrentEncoder(injector, expectedInput);

        Assert.assertThat(expectedOutput, equalTo(newRepeatStyleOutput));
        Assert.assertThat(expectedOutput, equalTo(oldRepeatStyleOutput));
    }

    @Test
    public void testMultipleRepeat3() throws IOException, EncoderException {
        String[] usingRepeatInput = new String[]{"REM Test repeat", "DELAY 500", "ESC", "DELAY 500", "CONTROL ESCAPE", "DELAY 500", "ESC", "DELAY 500", "DELAY 1000", "STRING ~", "DELAY 1000", "DOWN", "DOWN", "LEFT", "ENTER", "DELAY 1000", "STRING vz test", "DELAY 500", "DOWN", "ENTER", "STRING Starting SMS/MMS Test.", "ENTER", "STRING Char Count 141 ", "ENTER", "DELAY 1000", "STRING GixUp0N6mBZj7Q7uWN1G0Vec6XpJl@L2Y0AxQ7150ks9U3Uo3vz5lMdIL7M3R5gEuY7lT79@x5m7OR33Yy8xi4Vr2C190om6icZSSOsH5s2lfV9cAKFH35C0g3i8t21ag6t4AdFuxH61", "ENTER", "DELAY 1000", "REPEAT 3 3", "ESC", "REPEAT 3"};
        String[] notUsingRepeatInput = new String[]{"REM Test repeat", "DELAY 500", "ESC", "DELAY 500", "CONTROL ESCAPE", "DELAY 500", "ESC", "DELAY 500", "DELAY 1000", "STRING ~", "DELAY 1000", "DOWN", "DOWN", "LEFT", "ENTER", "DELAY 1000", "STRING vz test", "DELAY 500", "DOWN", "ENTER", "STRING Starting SMS/MMS Test.", "ENTER", "STRING Char Count 141 ", "ENTER", "DELAY 1000", "STRING GixUp0N6mBZj7Q7uWN1G0Vec6XpJl@L2Y0AxQ7150ks9U3Uo3vz5lMdIL7M3R5gEuY7lT79@x5m7OR33Yy8xi4Vr2C190om6icZSSOsH5s2lfV9cAKFH35C0g3i8t21ag6t4AdFuxH61", "ENTER", "DELAY 1000", "STRING GixUp0N6mBZj7Q7uWN1G0Vec6XpJl@L2Y0AxQ7150ks9U3Uo3vz5lMdIL7M3R5gEuY7lT79@x5m7OR33Yy8xi4Vr2C190om6icZSSOsH5s2lfV9cAKFH35C0g3i8t21ag6t4AdFuxH61", "ENTER", "DELAY 1000", "STRING GixUp0N6mBZj7Q7uWN1G0Vec6XpJl@L2Y0AxQ7150ks9U3Uo3vz5lMdIL7M3R5gEuY7lT79@x5m7OR33Yy8xi4Vr2C190om6icZSSOsH5s2lfV9cAKFH35C0g3i8t21ag6t4AdFuxH61", "ENTER", "DELAY 1000", "STRING GixUp0N6mBZj7Q7uWN1G0Vec6XpJl@L2Y0AxQ7150ks9U3Uo3vz5lMdIL7M3R5gEuY7lT79@x5m7OR33Yy8xi4Vr2C190om6icZSSOsH5s2lfV9cAKFH35C0g3i8t21ag6t4AdFuxH61", "ENTER", "DELAY 1000", "ESC", "ESC", "ESC", "ESC"};

        // Make sure the inputs are different
        Assert.assertThat(usingRepeatInput, not(equalTo(notUsingRepeatInput)));

        // Get the output from the current encoder
        byte[] usingRepeatOutput = IntegrationTestShared.getOutputFromCurrentEncoder(injector, usingRepeatInput);
        byte[] notUsingRepeatOutput = IntegrationTestShared.getOutputFromCurrentEncoder(injector, notUsingRepeatInput);

        Assert.assertThat(notUsingRepeatOutput, equalTo(usingRepeatOutput));
    }
}
