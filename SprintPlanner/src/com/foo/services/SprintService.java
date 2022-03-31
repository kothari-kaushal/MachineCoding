package com.foo.services;

import com.foo.exceptions.IllegalInputException;
import com.foo.model.Sprint;
import com.foo.repo.IRepo;

import java.util.Date;

public class SprintService {

    private final IRepo<Sprint> sprintRepo;

    public SprintService(IRepo<Sprint> sprintRepo) {

        this.sprintRepo = sprintRepo;
    }

    public Sprint getById(String id) {

        return this.sprintRepo.getById(id)
                .orElseThrow(() -> new IllegalInputException(String.format("sprint %s not found", id)));
    }

    public void createSprint(String id, Date startDate, Date endDate) {

        final Sprint sprint = new Sprint(id, startDate, endDate);
        sprintRepo.save(id, sprint);
        System.out.println(String.format("Sprint %s created successfully", id));
    }

}
