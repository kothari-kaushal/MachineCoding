package com.goodreads.dao;

import java.util.List;

public interface Repo<T> {

    T get(String id);

    List<T> getAll();

    T create(T obj);

    T update(T obj);

}
