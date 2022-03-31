package com.foo.services;

import com.foo.exceptions.IllegalInputException;
import com.foo.model.Sprint;
import com.foo.model.Task;
import com.foo.model.TaskStatus;
import com.foo.model.TaskType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Orchestrator {

    private final SprintService sprintService;
    private final TaskService taskService;

    private static final int MAX_TASKS = 20;
    private static final int MAX_IN_PROGRESS = 2;

    public Orchestrator(SprintService sprintService, TaskService taskService) {

        this.sprintService = sprintService;
        this.taskService = taskService;
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void createSprint(String id, String startDate, String endDate) {

        Date start, end;
        try {
            start = dateFormat.parse(startDate);
            end = dateFormat.parse(endDate);
        } catch (ParseException e) {
            throw new IllegalInputException("incorrect format for date", e);
        }
        sprintService.createSprint(id, start, end);
    }

    public void createTask(String id, String description, String taskType, String assignTo) {

        taskService.createTask(id, description, TaskType.from(taskType), assignTo);
    }

    public void addTask(String taskId, String sprintId) {

        Task task = taskService.getById(taskId);
        Sprint sprint = sprintService.getById(sprintId);

        synchronized (sprint.getId()) {
            if (sprint.getTasks().contains(taskId)) {
                throw new IllegalInputException(
                        String.format("task %s is already part of sprint %s", taskId, sprintId));
            }

            if (sprint.getTasks().size() >= MAX_TASKS) {
                throw new IllegalInputException(String.format("task limit for sprint %s exceeded", sprintId));
            }
            sprint.getTasks().add(taskId);
            System.out.println(String.format("task %s added to sprint %s", taskId, sprintId));
        }
    }

    public void removeTask(String taskId, String sprintId) {

        Task task = taskService.getById(taskId);
        Sprint sprint = sprintService.getById(sprintId);

        synchronized (sprint.getId()) {
            System.out.println("inside remove task");
            if (!sprint.getTasks().contains(taskId)) {
                throw new IllegalInputException(String.format("task %s is not part of sprint %s", taskId, sprintId));
            }

            sprint.getTasks().remove(taskId);
            System.out.println(String.format("task %s removed from sprint %s", taskId, sprintId));
        }
    }

    public void updateTask(String taskId, String toStatus) {

        Task task = taskService.getById(taskId);
        String user = task.getAssignedTo();

        List<Task> tasks = taskService.getAll();

        List<Task> tasksAssigned = tasks.stream().filter(task1 -> task1.getAssignedTo().equals(user))
                .filter(task1 -> TaskStatus.IN_PROGRESS.equals(task1.getStatus())).collect(Collectors.toList());
        if (tasksAssigned.size() >= MAX_IN_PROGRESS) {
            throw new IllegalInputException(
                    String.format("not more than %s tasks can be in progress for user", MAX_IN_PROGRESS, user));
        }

        taskService.updateStatus(task, TaskStatus.from(toStatus));
    }

    public List<String> tasksAssignedTo(String sprintId, String assignedTo) {

        Sprint sprint = sprintService.getById(sprintId);

        List<Task> tasksAssignedTo = new ArrayList<>();
        for (String taskId : sprint.getTasks()) {
            Task task = taskService.getById(taskId);
            if (task.getAssignedTo().equals(assignedTo)) {
                tasksAssignedTo.add(task);
            }
        }

        return tasksAssignedTo.stream().map(Task::getId).collect(Collectors.toList());
    }

    public List<String> delayedTasks(String sprintId) {

        Sprint sprint = sprintService.getById(sprintId);
        if (sprint.getEndDate().before(new Date())) {
            List<Task> delayedTasks = new ArrayList<>();
            for (String taskId : sprint.getTasks()) {
                Task task = taskService.getById(taskId);
                if (!task.getStatus().equals(TaskStatus.DONE)) {
                    delayedTasks.add(task);
                }
            }

            return delayedTasks.stream().map(Task::getId).collect(Collectors.toList());
        }

        return Collections.emptyList();

    }

}
