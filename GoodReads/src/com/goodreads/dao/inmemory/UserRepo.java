package com.goodreads.dao.inmemory;

import com.goodreads.dao.Repo;
import com.goodreads.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo implements Repo<User> {

    private List<User> users;

    public UserRepo() {

        this.users = new ArrayList<>();
    }

    @Override
    public User get(String id) {

        return users.stream().filter(user -> user.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalStateException("user not found"));
    }

    @Override
    public List<User> getAll() {

        return this.users;
    }

    @Override
    public User create(User obj) {

        this.users.add(obj);
        return obj;
    }

    @Override
    public User update(User obj) {

        return obj;
    }
}
