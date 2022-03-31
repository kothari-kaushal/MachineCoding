package com.foo;

import com.foo.repo.impl.InMemoryRepo;
import com.foo.services.Orchestrator;
import com.foo.services.SprintService;
import com.foo.services.TaskService;

import java.text.ParseException;

public class Application {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) throws ParseException, InterruptedException {

        SprintService sprintService = new SprintService(new InMemoryRepo<>());
        TaskService taskService = new TaskService(new InMemoryRepo<>());
        Orchestrator orch = new Orchestrator(sprintService, taskService);

        executeCommand(() -> orch.createSprint("sprint1", "2022-03-15", "2022-03-30"));

        executeCommand(() -> orch.createTask("task1", "foo bar", "Feature", "foo"));
        executeCommand(() -> orch.createTask("task1", "foo bar", "Feature", "foo"));
        executeCommand(() -> orch.createTask("task2", "foo bar", "Story", "foo"));

        executeCommand(() -> orch.addTask("task1", "sprint1"));

        executeCommand(() -> orch.addTask("task2", "sprint1"));

        executeCommand(() -> orch.createTask("task3", "foo bar", "Bug", "foo"));

        executeCommand(() -> orch.addTask("task3", "sprint1"));
        executeCommand(() -> orch.updateTask("task1", "done"));
        executeCommand(() -> orch.updateTask("task1", "progress"));
        executeCommand(() -> orch.updateTask("task2", "progress"));
        executeCommand(() -> orch.updateTask("task3", "progress"));

        executeCommand(() -> System.out.println(orch.tasksAssignedTo("sprint1", "foo")));
        executeCommand(() -> System.out.println(orch.delayedTasks("sprint1")));
    }

    private static void executeCommand(Command command) {

        try {
            command.execute();
        } catch (Exception e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
        }

    }

    private static interface Command {

        abstract void execute();

    }

}
