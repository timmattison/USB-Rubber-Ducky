package com.timmattison.hacking.usbrubberducky.exceptions;

/**
 * Created by timmattison on 7/31/14.
 */
public class NoParserFoundForStringException extends Exception {
    private final String string;

    public NoParserFoundForStringException(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
