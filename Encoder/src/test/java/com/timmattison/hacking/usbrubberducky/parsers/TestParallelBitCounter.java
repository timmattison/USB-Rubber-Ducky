package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.support.ParallelBitCounter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by timmattison on 7/30/14.
 */
public class TestParallelBitCounter {
    @Test
    public void testBitCounter1() {
        Assert.assertEquals(1, new ParallelBitCounter().countBits(0x1));
    }

    @Test
    public void testBitCounter2() {
        Assert.assertEquals(2, new ParallelBitCounter().countBits(0x3));
    }

    @Test
    public void testBitCounter31() {
        Assert.assertEquals(31, new ParallelBitCounter().countBits(0xEFFFFFFF));
    }

    @Test
    public void testBitCounter32() {
        Assert.assertEquals(32, new ParallelBitCounter().countBits(0xFFFFFFFF));
    }
}
