//package com.janani.twtdw.services;
//
//import com.janani.twtdw.configurations.TwitterConfiguration;
//import com.janani.twtdw.models.TwitterGetUserInfo;
//import com.janani.twtdw.resources.Tweet;
//import org.easymock.EasyMock;
//import org.easymock.EasyMockRunner;
//import org.easymock.Mock;
//import org.easymock.TestSubject;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import twitter4j.TwitterException;
//import java.util.List;
//
//
//@RunWith(EasyMockRunner.class)
//public class TwitterInterfaceImplTest {
//
//    @Mock
//    Tweet tweet;
//
//    @Mock
//    TwitterGetUserInfo twitterGetUserInfo;
//
//    @TestSubject
//    TwitterInterfaceImpl twitterInterfaceImpl = new TwitterInterfaceImpl();
//
//    @TestSubject
//    TwitterConfiguration twitterConfiguration = new TwitterConfiguration();
//
//    @Test
//    public void testPostTweet() throws TwitterException {
//        EasyMock.expect(tweet.getTweets()).andReturn("New Tweet! 1");
//        EasyMock.replay(tweet);
//        String val = twitterInterfaceImpl.postTweet(twitterConfiguration.twitterConfig(),tweet);
//        Assertions.assertEquals("New Tweet! 1", val);
//    }
//
//    @Test
//    public void testGetFilterTweets() throws TwitterException{
//        EasyMock.expect(tweet.getTweets()).andReturn("and");
//        EasyMock.replay(tweet);
//        List<String> val = twitterInterfaceImpl.getFilterTweets(twitterConfiguration.twitterConfig(),tweet);
//        Assertions.assertNotNull(val);
//    }
//
//    @Test
//    public void testGetTimeline() throws TwitterException{
//
//        List<TwitterGetUserInfo> val = twitterInterfaceImpl.getTimeline(twitterConfiguration.twitterConfig());
//        Assertions.assertNotNull(val);
//    }
//}