package com.timmattison.hacking.usbrubberducky.parsers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.timmattison.hacking.usbrubberducky.UsbRubberDuckyModule;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.instructions.lists.BasicInstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.InstructionList;
import com.timmattison.hacking.usbrubberducky.instructions.lists.processors.InstructionListProcessor;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by timmattison on 7/21/14.
 */
public class TestOriginalSyntaxIntegration {
    private static final String inputPath = "/inputs/original-syntax/";
    private static final String inputSuffix = ".txt";

    Injector injector;

    @Before
    public void setup() {
        injector = Guice.createInjector(new UsbRubberDuckyModule());
    }

    @Test
    public void testHelloWorld() throws Exception {
        String filename = "hello-world";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testDownloadAndExecute() throws Exception {
        String filename = "download-and-execute";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testPayloadNetcatFtpDownloadAndReverseShell() throws Exception {
        String filename = "netcat-ftp-download-and-reverse-shell";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testWallpaperPrank() throws Exception {
        String filename = "wallpaper-prank";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testHideCmdWindow() throws Exception {
        String filename = "hide-cmd-window-expanded";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testYouGotQuacked() throws Exception {
        String filename = "you-got-quacked";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testReverseShell() throws Exception {
        String filename = "reverse-shell";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testForkBomb() throws Exception {
        String filename = "fork-bomb";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testUtilmanExploit() throws Exception {
        String filename = "utilman-exploit";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testWifiBackdoor() throws Exception {
        String filename = "wifi-backdoor";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testNonMaliciousAutoDefacer() throws Exception {
        String filename = "non-malicious-auto-defacer";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testLockYourComputerMessage() throws Exception {
        String filename = "lock-your-computer-message";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testDuckyDownloader() throws Exception {
        String filename = "ducky-downloader";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testDuckyPhisher() throws Exception {
        String filename = "ducky-phisher";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testFtpUploadDownload() throws Exception {
        String filename = "ftp-upload-download";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testRestartPrank() throws Exception {
        String filename = "restart-prank";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testSillyMouseWindowsIsForKids() throws Exception {
        String filename = "silly-mouse-windows-is-for-kids";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testWindowsScreenRotationHack() throws Exception {
        String filename = "windows-screen-rotation-hack";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testPowershellWgetAndExecute() throws Exception {
        String filename = "powershell-wget-and-execute";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testPowershellWgetAndExecuteHidden() throws Exception {
        String filename = "powershell-wget-and-execute-hidden";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testMobileTabs() throws Exception {
        String filename = "mobile-tabs";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testCreateWirelessNetworkAssociation() throws Exception {
        String filename = "create-wireless-network-association";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testRetrieveSamAndSystemFromALiveFileSystem() throws Exception {
        String filename = "retrieve-sam-and-system-from-a-live-file-system";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testRetrieveSamAndSystemFromALiveFileSystem2() throws Exception {
        String filename = "retrieve-sam-and-system-from-a-live-file-system2";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testUglyRolledPrank() throws Exception {
        String filename = "ugly-rolled-prank";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testXmas() throws Exception {
        String filename = "xmas";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testPineappleAssociationVeryFast() throws Exception {
        String filename = "pineapple-association-very-fast";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxRootBackdoor() throws Exception {
        String filename = "osx-root-backdoor";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxLocalDnsPoisoning() throws Exception {
        String filename = "osx-local-dns-poisoning";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testMrGrayRubberHacksEncode() throws Exception {
        String filename = "mrgray-rubber-hacks-encode";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxUserBackdoor() throws Exception {
        String filename = "osx-user-backdoor";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testYoutubeBlaster() throws Exception {
        String filename = "osx-youtube-blaster";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxAsciiPrank() throws Exception {
        String filename = "osx-ascii-prank";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxGrabMinecraftAccountPasswordAndUploadToFtp() throws Exception {
        String filename = "osx-grab-minecraft-account-password-and-upload-to-ftp";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxWgetAndExecute() throws Exception {
        String filename = "osx-wget-and-execute";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxPasswordlessSshAccess() throws Exception {
        String filename = "osx-passwordless-ssh-access";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testOsxPasswordlessSshAccessTwinDuck() throws Exception {
        String filename = "osx-passwordless-ssh-access-twin-duck";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testBatchWiperDriveEraser() throws Exception {
        String filename = "batch-wiper-drive-eraser";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testGenericBatch() throws Exception {
        String filename = "generic-batch";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testPaintHack() throws Exception {
        String filename = "paint-hack";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testLocalDnsPoisoning() throws Exception {
        String filename = "local-dns-poisoning";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testDenyNetAccess() throws Exception {
        String filename = "deny-net-access";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testRunexeFromSd() throws Exception {
        String filename = "runexe-from-sd";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testRunexeFromSd2() throws Exception {
        String filename = "runexe-from-sd-2";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testRunJavaFromSd() throws Exception {
        String filename = "run-java-from-sd";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testMimikatz1() throws Exception {
        String filename = "mimikatz-1";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testMimikatz2() throws Exception {
        String filename = "mimikatz-2";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testWifunV1_1() throws Exception {
        String filename = "wifun-v1-1";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testMissDirection() throws Exception {
        String filename = "miss-direction";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testRemotelyPossible() throws Exception {
        String filename = "remotely-possible";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testCopyFileToDesktop() throws Exception {
        String filename = "copy-file-to-desktop";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testYoutubeRoll() throws Exception {
        String filename = "youtube-roll";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testDisableAvg2012() throws Exception {
        String filename = "disable-avg-2012";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testDisableAvg2013() throws Exception {
        String filename = "disable-avg-2013";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testEicarAvTest() throws Exception {
        String filename = "eicar-av-test";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testDownloadMimikatzGrabPasswordsAndEmailThemViaGmail() throws Exception {
        String filename = "download-mimikatz-grab-passwords-and-email-them-via-gmail";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }

    @Test
    public void testBigTestScript() throws Exception {
        String filename = "big-test-script";

        IntegrationTestShared.testFile(injector, inputPath, inputSuffix, filename);
    }
}

