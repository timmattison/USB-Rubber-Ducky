package com.timmattison.hacking.usbrubberducky.support;

/**
 * Created by timmattison on 7/30/14.
 */
public class ParallelBitCounter implements BitCounter {
    @Override
    public int countBits(int input) {
        // From https://stackoverflow.com/questions/109023/how-to-count-the-number-of-set-bits-in-a-32-bit-integer
        // Updated to use ">>>" for unsigned/bitwise shift instead of signed/logical shift
        input = input - ((input >>> 1) & 0x55555555);
        input = (input & 0x33333333) + ((input >>> 2) & 0x33333333);
        return (((input + (input >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
    }
}
