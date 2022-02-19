package com.newsfeed.service;

import com.newsfeed.dao.Repo;
import com.newsfeed.exception.CustomException;
import com.newsfeed.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PostService {

    private final AtomicInteger idGenerator = new AtomicInteger(0);
    private Repo<Post> postRepo;

    public PostService(Repo<Post> postRepo) {

        this.postRepo = postRepo;
    }

    public Post getById(String id) {

        return Optional.ofNullable(this.postRepo.getById(id))
                .orElseThrow(() -> new CustomException(String.format("post %s not found", id)));
    }

    public List<Post> getAll() {

        return this.postRepo.getAll();
    }

    public Post createNewPost(String userId, String content) {

        final String postId = String.format("%03d", idGenerator.getAndIncrement());
        Post newPost = new Post(postId, userId, content);
        this.postRepo.save(newPost);

        return newPost;
    }

    public Post upvote(String postId) {

        Post post = this.postRepo.getById(postId);
        int upvotes = post.getNumOfUpvote();
        post.setNumOfUpvote(upvotes + 1);

        return post;
    }

    public Post downvote(String postId) {

        Post post = this.postRepo.getById(postId);
        int downvotes = post.getNumOfDownvote();
        post.setNumOfDownvote(downvotes + 1);

        return post;
    }

    public void update(Post post) {

        this.postRepo.save(post);
    }

}
