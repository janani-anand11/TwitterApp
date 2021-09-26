package com.janani.twtdw.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
    @JsonProperty("tweets")
    public String tweets;

    public Tweet(){}

    public Tweet(String message) {
        this.tweets = message;
    }

    @JsonProperty
    public String getTweets() {
        return tweets;
    }

    @JsonProperty
    public void setTweets(String tweets) {
        this.tweets = tweets;
    }

}
