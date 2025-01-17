package com.sandeep.moviegrpcclient.command;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class MoviesPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("movies-shell> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}