package com.scheduler.entity;

import java.util.concurrent.TimeUnit;

public class Task {

    private Runnable Command;
    private long scheduledTime;
    private long period;
    private TimeUnit unit;

    public Task(Runnable command, long scheduledTime, long period, TimeUnit unit) {
        Command = command;
        this.scheduledTime = scheduledTime;
        this.period = period;
        this.unit = unit;
    }

    public Runnable getCommand() {
        return Command;
    }

    public void setCommand(Runnable command) {
        Command = command;
    }

    public long getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(long scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public boolean isPeriodic() {
        return period != 0;
    }
}
