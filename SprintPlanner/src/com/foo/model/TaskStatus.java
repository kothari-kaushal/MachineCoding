package com.foo.model;

import com.foo.exceptions.IllegalInputException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TaskStatus {

    TODO("todo"),
    IN_PROGRESS("progress"),
    DONE("done");

    private static final Map<String, TaskStatus> stringToEnum = Arrays.stream(TaskStatus.values())
            .collect(Collectors.toMap(TaskStatus::getStatus, Function.identity()));

    private final String status;

    TaskStatus(String status) {

        this.status = status;
    }

    public static TaskStatus from(String status) {

        return Optional.ofNullable(stringToEnum.get(status))
                .orElseThrow(() -> new IllegalInputException(String.format("%s is not a valid status", status)));
    }

    public String getStatus() {

        return status;
    }
}
