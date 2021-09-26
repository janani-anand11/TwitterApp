package com.janani.twtdw.services;

import com.janani.twtdw.models.TwitterGetUserInfo;
import com.janani.twtdw.resources.Tweet;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterInterfaceImpl implements TwitterInterface {

    public String postTweet(Twitter twitter,Tweet msg) throws TwitterException {
        Status status = twitter.updateStatus(msg.getTweets());
        return status.getText();
    }

    public List<TwitterGetUserInfo> getTimeline(Twitter twitter) throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        List<TwitterGetUserInfo> temp = new ArrayList<>();

        for(Status s: statuses){
            TwitterGetUserInfo userInfo = new TwitterGetUserInfo();
            userInfo.setCreatedAt(s.getCreatedAt().toString());
            userInfo.setMessage(s.getText());
            userInfo.setUserName(s.getUser().getName());
            userInfo.setTwitterHandle(s.getUser().getScreenName());
            userInfo.setProfileImageUrl(s.getUser().getProfileImageURL());
            temp.add(userInfo);
        }
        return temp;
    }

    public List<String> getFilterTweets(Twitter twitter, Tweet keyword) throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        String word = keyword.getTweets();
        List<String> filterTweets = statuses
                .stream()
                .filter(s-> s.getText().contains(word))
                .map(Status::getText)
                .collect(Collectors.toList());
        return filterTweets;
    }

}
