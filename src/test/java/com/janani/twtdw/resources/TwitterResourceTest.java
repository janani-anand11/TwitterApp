//package com.janani.twtdw.resources;
//
//import org.easymock.EasyMock;
//import org.easymock.EasyMockRunner;
//import org.easymock.Mock;
//import org.easymock.TestSubject;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import twitter4j.TwitterException;
//
//import javax.ws.rs.core.Response;
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(EasyMockRunner.class)
//public class TwitterResourceTest {
//
//    @Mock
//    Tweet tweet;
//
//    @TestSubject
//    TwitterResource twitterResource = new TwitterResource();
//
//    public TwitterResourceTest() throws TwitterException {
//    }
//
//    @Test
//    public void test1_tweetOut() throws TwitterException{
//        Response r;
//        EasyMock.expect(tweet.getTweets()).andReturn("new tweet");
//        EasyMock.replay(tweet);
//        r = twitterResource.tweetOut(tweet);
//        assertEquals("Successfully posted!",r.getEntity());
//    }
//
//    @Test
//    public void test2_tweetOutException() throws TwitterException {
//        Response r;
//        EasyMock.expect(tweet.getTweets()).andReturn("new tweet");
//        EasyMock.replay(tweet);
//        r = twitterResource.tweetOut(tweet);
//        assertEquals("Failed to post tweet.",r.getEntity());
//    }
//
//    @Test
//    public void test3_getTimeline() throws TwitterException{
//        Response r;
//        r = twitterResource.getTimeline();
//        Assert.assertNotNull(r.getEntity());
//    }
//
//    @Test
//    public void test4_getFilteredTweets() throws TwitterException{
//        Response r;
//        EasyMock.expect(tweet.getTweets()).andReturn("the");
//        EasyMock.replay(tweet);
//        r = twitterResource.getFilteredTweets(tweet);
//        Assert.assertNotNull(r.getEntity());
//    }
//
//}