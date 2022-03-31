package com.foo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Sprint {

    private String id;
    private Date startDate;
    private Date endDate;
    private Set<String> tasks;

    public Sprint(String id, Date startDate, Date endDate) {

        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasks = new HashSet<>();
    }

    public String getId() {

        return id;
    }

    public Date getStartDate() {

        return startDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public Set<String> getTasks() {

        return tasks;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }

    public void setTasks(Set<String> tasks) {

        this.tasks = tasks;
    }
}
