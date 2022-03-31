package com.foo.services;

import com.foo.exceptions.IllegalInputException;
import com.foo.model.Task;
import com.foo.model.TaskStatus;
import com.foo.model.TaskType;
import com.foo.repo.IRepo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskService {

    private final IRepo<Task> taskRepo;

    private static final Map<TaskStatus, Set<TaskStatus>> transitionsAllowed = new HashMap<>();

    static {
        transitionsAllowed.put(TaskStatus.TODO, new HashSet<>(Arrays.asList(TaskStatus.IN_PROGRESS)));
        transitionsAllowed.put(TaskStatus.IN_PROGRESS, new HashSet<>(Arrays.asList(TaskStatus.TODO, TaskStatus.DONE)));
    }

    public TaskService(IRepo<Task> taskRepo) {

        this.taskRepo = taskRepo;
    }

    public Task getById(String id) {

        return this.taskRepo.getById(id)
                .orElseThrow(() -> new IllegalInputException(String.format("task %s not found", id)));
    }

    public List<Task> getAll() {

        return this.taskRepo.getAll();
    }

    public void createTask(String id, String description, TaskType type, String assignTo) {

        final Task task = new Task(id, description, type, TaskStatus.TODO, assignTo);
        taskRepo.save(id, task);
        System.out.println(String.format("task %s created successfully", id));
    }

    public void updateStatus(Task task, TaskStatus toStatus) {

        validateStateTransition(task.getStatus(), toStatus);
        task.setStatus(toStatus);
        taskRepo.update(task.getId(), task);
        System.out.println(String.format("task %s updated to %s", task.getId(), task.getStatus().getStatus()));
    }

    private void validateStateTransition(TaskStatus fromStatus, TaskStatus toStatus) {

        if (!transitionsAllowed.get(fromStatus).contains(toStatus)) {
            String errMsg = String.format("transition not allowed from %s to %s", fromStatus.getStatus(),
                    toStatus.getStatus());
            throw new IllegalInputException(errMsg);
        }
    }

}
