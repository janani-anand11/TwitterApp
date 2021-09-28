package com.janani.twtdw.services;

import com.janani.twtdw.models.TwitterGetUserInfo;
import com.janani.twtdw.models.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

public interface TwitterInterface {
     List<TwitterGetUserInfo> getTimeline(Twitter twitter) throws TwitterException;
     String postTweet(Twitter twitter,Tweet msg) throws TwitterException;
     List<String> getFilterTweets(Twitter twitter,Tweet keyword) throws TwitterException;
}
