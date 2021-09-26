package com.janani.twtdw.services;

import com.janani.twtdw.resources.Tweet;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

public class TwitterInterfaceImpl implements TwitterInterface {

    public String postTweet(Twitter twitter,Tweet msg) throws TwitterException {
        Status status = twitter.updateStatus(msg.getTweets());
        return status.getText();
    }

    public List<String> getTimeline(Twitter twitter) throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        List<String> temp = new ArrayList<>();

        for(Status s: statuses){
            temp.add(s.getUser().getName()+": "+s.getText());
        }
        return temp;
    }

}
