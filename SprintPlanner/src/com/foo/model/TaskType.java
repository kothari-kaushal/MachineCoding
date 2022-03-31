package com.foo.model;

import com.foo.exceptions.IllegalInputException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TaskType {

    FEATURE("Feature"),
    BUG("Bug"),
    STORY("Story");

    private static final Map<String, TaskType> stringToEnum = Arrays.stream(TaskType.values())
            .collect(Collectors.toMap(TaskType::getTaskType, Function.identity()));

    private final String taskType;

    TaskType(String taskType) {

        this.taskType = taskType;
    }

    public static TaskType from(String taskType) {

        return Optional.ofNullable(stringToEnum.get(taskType)).orElseThrow(() -> new IllegalInputException(
                String.format("%s is not a valid taskType", taskType)));
    }

    public String getTaskType() {

        return taskType;
    }
}
