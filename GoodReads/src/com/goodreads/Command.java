package com.goodreads;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Command {

    REGISTER("reg"),
    LOGIN("login"),
    LOGOUT("logout"),
    NEXT("next"),
    PREV("prev"),
    START_READ("start"),
    RESUME("resume"),
    PURCHASE("purchase"),
    ADD("add"),
    GOTO("goto"),
    LIST("list"),
    RATE("rate");

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
                .orElseThrow(() -> new IllegalStateException("Command not supported"));
    }

}
