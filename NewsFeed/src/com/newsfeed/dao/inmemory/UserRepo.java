package com.newsfeed.dao.inmemory;

import com.newsfeed.dao.Repo;
import com.newsfeed.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo implements Repo<User> {

    private Map<String, User> usersById;

    public UserRepo() {

        this.usersById = new HashMap<>();
    }

    @Override
    public User getById(String id) {

        return this.usersById.get(id);
    }

    @Override
    public List<User> getAll() {

        return new ArrayList<>(this.usersById.values());
    }

    @Override
    public User save(User obj) {

        this.usersById.put(obj.getId(), obj);
        return obj;
    }
}
