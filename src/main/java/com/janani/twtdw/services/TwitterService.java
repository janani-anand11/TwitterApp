package com.janani.twtdw.services;

import com.janani.twtdw.models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    TwitterInterface twitterInterface;

    public String getTimeline(Twitter twitter) throws TwitterException{
        return twitterInterface.getTimeline(twitter);
    }
    public String postTweet(Twitter twitter, Tweet msg) throws TwitterException{
        return twitterInterface.postTweet(twitter,msg);
    }
    public List<String> getFilterTweets(Twitter twitter, Tweet keyword) throws TwitterException {
        return twitterInterface.getFilterTweets(twitter, keyword);
    }
}
