package com.janani.twtdw.services;

import com.janani.twtdw.resources.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

public class TwitterService {

    TwitterInterfaceImpl twitterInterface = new TwitterInterfaceImpl();

    public List<String> getTimeline(Twitter twitter) throws TwitterException{
        return twitterInterface.getTimeline(twitter);
    }
    public String postTweet(Twitter twitter, Tweet msg) throws TwitterException{
        return twitterInterface.postTweet(twitter,msg);
    }

}
