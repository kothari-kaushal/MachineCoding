package com.newsfeed.service;

import com.newsfeed.dao.Repo;
import com.newsfeed.exception.CustomException;
import com.newsfeed.model.Reply;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ReplyService {

    private final AtomicInteger idGenerator = new AtomicInteger(0);
    private final Repo<Reply> repliesRepo;

    public ReplyService(Repo<Reply> repliesRepo) {

        this.repliesRepo = repliesRepo;
    }

    public Reply getById(String id) {

        return Optional.ofNullable(this.repliesRepo.getById(id))
                .orElseThrow(() -> new CustomException(String.format("reply %s not found", id)));
    }

    public Reply newReply(String userId, String postId, String content) {

        final String replyId = String.format("%03d", idGenerator.getAndIncrement());
        Reply reply = new Reply(replyId, userId, postId, content);
        return this.repliesRepo.save(reply);
    }

    public Reply upvote(String postId) {

        Reply reply = this.repliesRepo.getById(postId);
        int upvotes = reply.getNumOfUpvote();
        reply.setNumOfUpvote(upvotes + 1);

        this.repliesRepo.save(reply);

        return reply;
    }

    public Reply downvote(String postId) {

        Reply reply = this.repliesRepo.getById(postId);
        int downvotes = reply.getNumOfDownvote();
        reply.setNumOfDownvote(downvotes + 1);

        this.repliesRepo.save(reply);

        return reply;
    }
}
