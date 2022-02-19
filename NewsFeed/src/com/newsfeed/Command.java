package com.newsfeed;

import com.newsfeed.exception.CustomException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Command {

    SIGNUP("signup"),
    LOGIN("login"),
    POST("post"),
    FOLLOW("follow"),
    SHOW_FEED("shownewsfeed"),
    REPLY("reply"),
    UPVOTE("upvote"),
    DOWNVOTE("downvote");

    private final String command;

    private static final Map<String, Command> STRING_TO_ENUM = Arrays.stream(Command.values())
            .collect(Collectors.toMap(Command::getCommand, Function.identity()));

    Command(String command) {

        this.command = command;
    }

    public String getCommand() {

        return command;
    }

    public static Command from(String command) {

        return Optional.ofNullable(STRING_TO_ENUM.get(command))
                .orElseThrow(() -> new CustomException(String.format("Command %s not supported", command)));
    }

}
