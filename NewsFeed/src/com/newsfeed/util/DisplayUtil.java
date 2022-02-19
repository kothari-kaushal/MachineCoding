package com.newsfeed.util;

import com.newsfeed.NewsFeed;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class DisplayUtil {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm a");

    private static final String BLUE_COLOUR = "\033[0;34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static String convertToHumanReadable(long timeStamp) {

        return dateFormat.format(timeStamp);
    }

    public static void displayFeed(List<NewsFeed> feeds) {

        feeds.forEach(feed -> {

            System.out.println(feed.getPostId());
            System.out.println(
                    "(" + feed.getNumOfUpVote() + " upvote, " + feed.getNumOfDownVote() + " downvotes" + ")");
            System.out.println(feed.getPostedBy());
            System.out.println(feed.getPostContent());
            System.out.println(feed.getPostedTimeStamp());

            feed.getReplyFeeds().forEach(replyFeed -> {
                System.out.print(BLUE_COLOUR);
                System.out.println(replyFeed.getCommentId());
                System.out.println(replyFeed.getCommentBy());
                System.out.println(replyFeed.getCommentContent());
                System.out.println(replyFeed.getCommentedTimeStamp());
                System.out.print(ANSI_RESET);
            });
        });
    }

    public static void main(String[] args) {

        System.out.println(convertToHumanReadable(System.currentTimeMillis()));
        System.out.println(String.format("%03d", 10));
    }

}
