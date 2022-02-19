package com.newsfeed.service;

import com.newsfeed.NewsFeed;
import com.newsfeed.ReplyFeed;
import com.newsfeed.dao.Repo;
import com.newsfeed.exception.CustomException;
import com.newsfeed.model.Post;
import com.newsfeed.model.Reply;
import com.newsfeed.model.User;
import com.newsfeed.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private final Repo<User> userRepo;
    private final PostService postService;
    private final ReplyService replyService;

    public UserService(Repo<User> userRepo, PostService postService, ReplyService replyService) {

        this.userRepo = userRepo;
        this.postService = postService;
        this.replyService = replyService;
    }

    public User getById(String id) {

        return Optional.ofNullable(this.userRepo.getById(id))
                .orElseThrow(() -> new CustomException(String.format("user %s not found", id)));
    }

    public User register(String id, String name) {

        User check = this.userRepo.getById(id);
        if (check != null) {
            throw new CustomException(String.format("user %s already exists", id));
        }

        User newUser = new User(id, name);
        return this.userRepo.save(newUser);
    }

    public User login(String id) {

        User user = this.getById(id);

        user.setLoggedIn(true);
        return this.userRepo.save(user);
    }

    public void upvote(String userId, String postId) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        this.postService.upvote(postId);
    }

    public void downvote(String userId, String postId) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        this.postService.downvote(postId);
    }

    public void newPost(String userId, String content) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        this.postService.createNewPost(userId, content);
    }

    public void follow(String userId, String targetUserId) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        User targetUser = this.getById(targetUserId);
        targetUser.getFollowers().add(userId);
        this.userRepo.save(targetUser);

        user.getFollowing().add(targetUserId);
        this.userRepo.save(user);
    }

    public void comment(String userId, String postId, String content) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        Post post = this.postService.getById(postId);

        Reply reply = this.replyService.newReply(userId, postId, content);

        post.getReplies().add(reply.getId());
        this.postService.update(post);
    }

    public void upvoteComment(String userId, String postId) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        this.postService.getById(postId);

        this.replyService.upvote(postId);
    }

    public void downVoteComment(String userId, String postId) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        this.postService.getById(postId);

        this.replyService.downvote(postId);
    }

    public List<NewsFeed> getFeed(String userId) {

        User user = this.getById(userId);
        validateUserLoggedIn(user);

        List<Post> posts = this.postService.getAll();

        posts.sort((p1, p2) -> {

            boolean followerPost1 = user.getFollowers().contains(p1.getUserId());
            boolean followerPost2 = user.getFollowers().contains(p2.getUserId());

            if (followerPost1 && followerPost2) {
                Integer score1 = p1.getNumOfUpvote() - p1.getNumOfDownvote();
                Integer score2 = p2.getNumOfUpvote() - p2.getNumOfDownvote();
                if (score1.equals(score2)) {
                    Integer numOfComments1 = p1.getReplies().size();
                    Integer numOfComments2 = p2.getReplies().size();
                    if (numOfComments1.equals(numOfComments2)) {
                        return Long.compare(p1.getUpdatedAt(), p2.getUpdatedAt());
                    } else {
                        return numOfComments1.compareTo(numOfComments2);
                    }
                } else {
                    return score1.compareTo(score2);
                }
            } else if (followerPost1) {
                return -1;
            } else {
                return 1;
            }
        });

        List<NewsFeed> feeds = new ArrayList<>();
        posts.forEach(post -> {

            NewsFeed feed = new NewsFeed();
            feed.setPostId(post.getId());
            feed.setNumOfUpVote(post.getNumOfUpvote());
            feed.setNumOfDownVote(post.getNumOfDownvote());
            feed.setPostedBy(post.getUserId());
            feed.setPostContent(post.getContent());
            feed.setPostedTimeStamp(DisplayUtil.convertToHumanReadable(post.getUpdatedAt()));

            List<ReplyFeed> replyFeeds = post.getReplies().stream().map(replyService::getById).map(reply -> {
                ReplyFeed replyFeed = new ReplyFeed();
                replyFeed.setCommentId(reply.getId());
                replyFeed.setCommentContent(reply.getContent());
                replyFeed.setCommentBy(reply.getUserId());
                replyFeed.setCommentedTimeStamp(DisplayUtil.convertToHumanReadable(reply.getUpdatedAt()));

                return replyFeed;
            }).collect(Collectors.toList());

            feed.setReplyFeeds(replyFeeds);

            feeds.add(feed);
        });

        return feeds;
    }

    private void validateUserLoggedIn(User user) {

        if (!user.isLoggedIn()) {
            throw new CustomException("Please login first");
        }
    }

}
