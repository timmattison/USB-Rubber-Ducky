package com.timmattison.hacking.usbrubberducky.instructions;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/27/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractInstruction implements Instruction {
    private final String input;
    private String data;

    public AbstractInstruction(String input) {
        this.input = new String(input);
    }

    public String getInput() {
        return input;
    }

    public String getData() {
        if(data == null) {
            data = setupData();
        }

        return data;
    }

    protected abstract String setupData();
}
