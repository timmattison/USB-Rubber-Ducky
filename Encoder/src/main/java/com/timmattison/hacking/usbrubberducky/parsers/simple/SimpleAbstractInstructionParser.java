package com.timmattison.hacking.usbrubberducky.parsers.simple;

import com.google.inject.Inject;
import com.timmattison.hacking.usbrubberducky.instructions.Instruction;
import com.timmattison.hacking.usbrubberducky.parsers.InstructionParser;
import com.timmattison.hacking.usbrubberducky.preprocessors.Preprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: timmattison
 * Date: 11/27/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class SimpleAbstractInstructionParser<T extends Instruction> implements InstructionParser<T> {
    protected final Preprocessor preprocessor;
    private Pattern pattern;

    @Inject
    public SimpleAbstractInstructionParser(Preprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    private Pattern getPattern() {
        if(pattern == null) {
            pattern = Pattern.compile(getMatchingRegex());
        }

        return pattern;
    }

    private Matcher getMatcher(String input) {
        return getPattern().matcher(input);
    }

    @Override
    public T parse(String input) {
        // Preprocess the input
        input = preprocessor.preprocess(input);

        Matcher matcher = getMatcher(input);

        // Does this input match what we're expecting for this instruction?
        if (!matcher.find()) {
            // No, just return NULL
            return null;
        }

        // Now we know the input matches, create the instruction
        List<String> fields = new ArrayList<String>();

        for(int loop = 0; loop < matcher.groupCount(); loop++) {
            fields.add(matcher.group(loop + 1));
        }

        // Extract all of the groups
        return create(fields);
    }

    /**
     * Creates an instruction once we know that the input matches the regex
     *
     *
     * @param input
     * @return
     */
    protected abstract T create(List<String> input);

    /**
     * Returns the regex that an input must match for this instruction
     *
     * @return
     */
    protected abstract String getMatchingRegex();
}
