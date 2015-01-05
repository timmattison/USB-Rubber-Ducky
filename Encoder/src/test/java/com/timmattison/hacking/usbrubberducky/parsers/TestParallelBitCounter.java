package com.timmattison.hacking.usbrubberducky.parsers;

import com.timmattison.hacking.usbrubberducky.support.ParallelBitCounter;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by timmattison on 7/30/14.
 */
public class TestParallelBitCounter {
    @Test
    public void testBitCounter1() {
        Assert.assertThat(1, is(new ParallelBitCounter().countBits(0x1)));
    }

    @Test
    public void testBitCounter2() {
        Assert.assertThat(2, is(new ParallelBitCounter().countBits(0x3)));
    }

    @Test
    public void testBitCounter31() {
        Assert.assertThat(31, is(new ParallelBitCounter().countBits(0xEFFFFFFF)));
    }

    @Test
    public void testBitCounter32() {
        Assert.assertThat(32, is(new ParallelBitCounter().countBits(0xFFFFFFFF)));
    }
}
