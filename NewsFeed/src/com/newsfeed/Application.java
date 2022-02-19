package com.newsfeed;

import com.newsfeed.dao.inmemory.PostRepo;
import com.newsfeed.dao.inmemory.ReplyRepo;
import com.newsfeed.dao.inmemory.UserRepo;
import com.newsfeed.exception.CustomException;
import com.newsfeed.model.User;
import com.newsfeed.service.PostService;
import com.newsfeed.service.ReplyService;
import com.newsfeed.service.UserService;
import com.newsfeed.util.DisplayUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Application {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    private static Optional<User> currentUser = Optional.empty();

    public static void main(String[] args) throws IOException {

        PostService postService = new PostService(new PostRepo());
        ReplyService replyService = new ReplyService(new ReplyRepo());

        final UserService userService = new UserService(new UserRepo(), postService, replyService);

        while (true) {
            try {
                String input = BUFFERED_READER.readLine();
                String[] params = input.split("~");
                Command command = Command.from(params[0]);
                switch (command) {
                    case SIGNUP:
                        userService.register(params[1], params[1]);
                        break;
                    case LOGIN:
                        currentUser = Optional.of(userService.login(params[1]));
                        break;
                    case POST:
                        userService.newPost(getCurrentUser().getId(), params[1]);
                        break;
                    case UPVOTE:
                        userService.upvote(getCurrentUser().getId(), params[1]);
                        break;
                    case DOWNVOTE:
                        userService.downvote(getCurrentUser().getId(), params[1]);
                        break;
                    case REPLY:
                        userService.comment(getCurrentUser().getId(), params[1], params[2]);
                        break;
                    case SHOW_FEED:
                        DisplayUtil.displayFeed(userService.getFeed(getCurrentUser().getId()));
                        break;
                    case FOLLOW:
                        userService.follow(getCurrentUser().getId(), params[1]);
                        break;
                    default:
                        throw new CustomException(String.format("command %s not supported", params[0]));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private static User getCurrentUser() {

        return currentUser.orElseThrow(() -> new CustomException("you need to login first.!!"));
    }

}
