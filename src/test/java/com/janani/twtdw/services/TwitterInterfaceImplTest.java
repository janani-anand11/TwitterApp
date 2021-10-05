package com.janani.twtdw.services;

import com.janani.twtdw.configurations.twitterConfig.TwitterConfiguration;
import com.janani.twtdw.models.Tweet;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import twitter4j.TwitterException;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TwitterInterfaceImplTest {

    @Mock
    Tweet tweet;

    @InjectMocks
    TwitterInterfaceImpl twitterInterfaceImpl = new TwitterInterfaceImpl();

    TwitterConfiguration twitterConfiguration = new TwitterConfiguration();

    @Test
    public void testPostTweet() throws TwitterException {
        Mockito.when(tweet.getTweets()).thenReturn("New Tweet! 1");
        String val = twitterInterfaceImpl.postTweet(twitterConfiguration.twitterConfig(),tweet);
        Assertions.assertEquals("New Tweet! 1", val);
    }

    @Test
    public void testGetFilterTweets() throws TwitterException{
        Mockito.when(tweet.getTweets()).thenReturn("and");
        List<String> val = twitterInterfaceImpl.getFilterTweets(twitterConfiguration.twitterConfig(),tweet);
        Assertions.assertNotNull(val);
    }

    @Test
    public void testGetTimeline() throws TwitterException{
        String val = twitterInterfaceImpl.getTimeline(twitterConfiguration.twitterConfig());
        Assertions.assertNotNull(val);
    }
}