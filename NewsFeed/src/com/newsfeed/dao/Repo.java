package com.newsfeed.dao;

import java.util.List;

public interface Repo<T> {

    T getById(String id);

    List<T> getAll();

    T save(T obj);

}
