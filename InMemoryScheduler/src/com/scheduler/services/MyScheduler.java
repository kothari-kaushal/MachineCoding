package com.scheduler.services;

import com.scheduler.entity.Task;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyScheduler implements IExecutorService {

    private PriorityQueue<Task> queue;

    private Lock lock = new ReentrantLock();
    private Condition newTaskAdded = lock.newCondition();

    public MyScheduler() {
        this.queue = new PriorityQueue<>(Comparator.comparing(Task::getScheduledTime));
        Thread thread = new Thread(new Worker());
        thread.start();
    }

    @Override
    public void schedule(Runnable command, long delay, TimeUnit unit) {
        Task task = new Task(command, System.currentTimeMillis() + delay, 0, unit);
        addToQueue(task);
    }

    @Override
    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        Task task = new Task(command, System.currentTimeMillis() + initialDelay, period, unit);
        addToQueue(task);
    }

    @Override
    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        Task task = new Task(command, System.currentTimeMillis() + initialDelay, delay, unit);
        addToQueue(task);
    }

    private void addToQueue(Task task) {
        lock.lock();
        queue.add(task);
        newTaskAdded.signal();
        lock.unlock();
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    while (queue.isEmpty()) {
                        newTaskAdded.await();
                    }
                    Task task = queue.peek();
                    while (task != null && task.getScheduledTime() > System.currentTimeMillis()) {
                        long sleepTime = System.currentTimeMillis() - task.getScheduledTime();
                        newTaskAdded.await(sleepTime, TimeUnit.MILLISECONDS);
                        task = queue.peek();
                    }
                    task = queue.poll();
                    if (task != null) {
                        lock.unlock();
                        task.getCommand().run();
                        if (task.isPeriodic()) {
                            task.setScheduledTime(System.currentTimeMillis() + task.getUnit().toMillis(task.getPeriod()));
                            addToQueue(task);
                        }
                    }
                } catch (InterruptedException e) {
                    lock.unlock();
                    e.printStackTrace();
                }
            }
        }
    }

}
