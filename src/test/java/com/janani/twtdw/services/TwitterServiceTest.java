package com.janani.twtdw.services;

import com.janani.twtdw.configurations.twitterConfig.TwitterConfiguration;
import com.janani.twtdw.models.Tweet;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.TwitterException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class TwitterServiceTest {

    @Mock
    private TwitterInterfaceImpl twitterInterface;

    @Mock
    Tweet tweet;

    @InjectMocks
    private TwitterService twitterService;

    TwitterConfiguration twitterConfiguration = new TwitterConfiguration();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getTimeline() throws TwitterException {
        Mockito.when(twitterInterface.getTimeline(twitterConfiguration.twitterConfig())).thenReturn("");
        String timeline = twitterService.getTimeline(twitterConfiguration.twitterConfig());
        Assert.assertNotNull(timeline);
    }

    @Test
    void postTweet()  throws TwitterException {
        Mockito.when(tweet.getTweets()).thenReturn("new Tweet");
        Mockito.when(twitterInterface.postTweet(twitterConfiguration.twitterConfig(),tweet)).thenReturn("new Tweet");
        String postTweet = twitterService.postTweet(twitterConfiguration.twitterConfig(),tweet);
        Assert.assertEquals("new Tweet",postTweet);
    }

    @Test
    void getFilterTweets() throws TwitterException{
        Mockito.when(tweet.getTweets()).thenReturn("and");
        List<String> filterTweets = new ArrayList<>();
        Mockito.when(twitterInterface.getFilterTweets(twitterConfiguration.twitterConfig(),tweet)).thenReturn(filterTweets);
        List<String> output = twitterService.getFilterTweets(twitterConfiguration.twitterConfig(),tweet);
        Assert.assertNotNull(output);
    }
}