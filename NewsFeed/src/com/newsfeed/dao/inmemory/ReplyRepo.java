package com.newsfeed.dao.inmemory;

import com.newsfeed.dao.Repo;
import com.newsfeed.model.Reply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplyRepo implements Repo<Reply> {

    private Map<String, Reply> repliesById;

    public ReplyRepo() {

        this.repliesById = new HashMap<>();
    }

    @Override
    public Reply getById(String id) {

        return this.repliesById.get(id);
    }

    @Override
    public List<Reply> getAll() {

        return new ArrayList<>(this.repliesById.values());
    }

    @Override
    public Reply save(Reply obj) {

        this.repliesById.put(obj.getId(), obj);
        return obj;
    }
}
