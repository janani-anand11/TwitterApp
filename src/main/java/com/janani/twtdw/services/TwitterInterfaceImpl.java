package com.janani.twtdw.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.janani.twtdw.models.TwitterGetUserInfo;
import com.janani.twtdw.models.Tweet;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class TwitterInterfaceImpl implements TwitterInterface {

    public String postTweet(Twitter twitter,Tweet msg) throws TwitterException {
        Status status = twitter.updateStatus(msg.getTweets());
        return status.getText();
    }

    public String getTimeline(Twitter twitter) throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        List<TwitterGetUserInfo> temp = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for(Status s: statuses){
            TwitterGetUserInfo userInfo = new TwitterGetUserInfo();
            userInfo.setCreatedAt(s.getCreatedAt().toString());
            userInfo.setMessage(s.getText());
            userInfo.setUserName(s.getUser().getName());
            userInfo.setTwitterHandle(s.getUser().getScreenName());
            userInfo.setProfileImageUrl(s.getUser().getProfileImageURL());
            userInfo.setStatusUrl("https://twitter.com/" + s.getUser().getScreenName()
                    + "/status/" + s.getId());
            temp.add(userInfo);
        }
        String json = gson.toJson(temp);
        return json;
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
