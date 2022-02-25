package com.yoyo.dao;

import java.util.List;

public interface Repo<T> {

    T getById(String id);

    List<T> getAll();

    T save(String id, T obj);

}
