package com.foo.repo.impl;

import com.foo.exceptions.IllegalInputException;
import com.foo.repo.IRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepo<T> implements IRepo<T> {

    private final Map<String, T> idToEntity;

    public InMemoryRepo() {

        this.idToEntity = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<T> getById(String id) {

        return Optional.ofNullable(idToEntity.get(id));
    }

    @Override
    public List<T> getAll() {

        return new ArrayList<>(this.idToEntity.values());
    }

    @Override
    public T save(String id, T obj) {

        if (this.getById(id).isPresent()) {
            throw new IllegalInputException(String.format("id %s already exists", id));
        }

        idToEntity.put(id, obj);
        return obj;
    }

    @Override
    public T update(String id, T obj) {

        return null;
    }
}
