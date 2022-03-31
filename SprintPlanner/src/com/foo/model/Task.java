package com.foo.model;

public class Task {
    private String id;
    private String description;
    private TaskType type;
    private TaskStatus status;
    private String assignedTo;

    public Task(String id, String description, TaskType type, TaskStatus status, String assignedTo) {

        this.id = id;
        this.description = description;
        this.type = type;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    public String getId() {

        return id;
    }

    public String getDescription() {

        return description;
    }

    public TaskType getType() {

        return type;
    }

    public TaskStatus getStatus() {

        return status;
    }

    public String getAssignedTo() {

        return assignedTo;
    }

    public void setStatus(TaskStatus status) {

        this.status = status;
    }

    public void setAssignedTo(String assignedTo) {

        this.assignedTo = assignedTo;
    }
}
