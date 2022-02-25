package com.yoyo.dao.inmemory;

import com.yoyo.dao.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepo<T> implements Repo<T> {

    private final Map<String, T> itemsById;

    public InMemoryRepo() {

        this.itemsById = new ConcurrentHashMap<>();
    }

    @Override
    public T getById(String id) {

        return this.itemsById.get(id);
    }

    @Override
    public List<T> getAll() {

        return new ArrayList<>(this.itemsById.values());
    }

    @Override
    public T save(String id, T obj) {

        this.itemsById.put(id, obj);
        return obj;
    }
}
