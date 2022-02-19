package com.newsfeed.dao.inmemory;

import com.newsfeed.dao.Repo;
import com.newsfeed.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepo implements Repo<Post> {

    private Map<String, Post> postsById;

    public PostRepo() {

        this.postsById = new HashMap<>();
    }

    @Override
    public Post getById(String id) {

        return this.postsById.get(id);
    }

    @Override
    public List<Post> getAll() {

        return new ArrayList<>(this.postsById.values());
    }

    @Override
    public Post save(Post obj) {

        this.postsById.put(obj.getId(), obj);
        return obj;
    }
}
