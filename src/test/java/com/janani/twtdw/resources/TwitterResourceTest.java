package com.janani.twtdw.resources;

import com.janani.twtdw.configurations.twitterConfig.TwitterConfiguration;
import com.janani.twtdw.models.Tweet;
import com.janani.twtdw.services.TwitterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import twitter4j.TwitterException;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterResourceTest {
    @Mock
    private Tweet tweet;

    @Mock
    private TwitterConfiguration twitterConfiguration;

    @Mock
    private TwitterService twitterService;

    @InjectMocks
    private TwitterResource twitterResource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1_tweetOut() throws TwitterException {
        Mockito.when(tweet.getTweets()).thenReturn("new Tweet");
        Response r = twitterResource.tweetOut(tweet);
        assertEquals("Successfully posted!",r.getEntity());
    }

    @Test
    public void test2_getTimeline() throws TwitterException {
        Mockito.when(twitterService.getTimeline(twitterConfiguration.twitterConfig())).thenReturn("");
        String val = twitterResource.getTimeline();
        Assertions.assertNotNull(val);
    }

    @Test
    public void test3_getFilterTimeline() throws TwitterException {
        Mockito.when(tweet.getTweets()).thenReturn("and");
        List<String> val = twitterResource.getFilteredTweets(tweet);
        Assertions.assertNotNull(val);
    }
}
