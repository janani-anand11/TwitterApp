package com.janani.twtdw.services;

import com.janani.twtdw.resources.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

public interface TwitterInterface {
     List<String> getTimeline(Twitter twitter) throws TwitterException;
     String postTweet(Twitter twitter,Tweet msg) throws TwitterException;
}
