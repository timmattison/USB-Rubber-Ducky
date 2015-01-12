package com.timmattison.hacking.usbrubberducky;

import com.timmattison.hacking.usbrubberducky.exceptions.EncoderException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 10/25/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RubberDuckyEncoder {
    public byte[] encode(String[] input) throws IOException, EncoderException;
}
