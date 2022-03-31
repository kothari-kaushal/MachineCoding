package com.foo.repo;

import java.util.List;
import java.util.Optional;

public interface IRepo<T> {

    Optional<T> getById(String id);

    List<T> getAll();

    T save(String id, T obj);

    T update(String id, T obj);

}
