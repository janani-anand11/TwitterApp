package com.janani.twtdw.resources;

import com.codahale.metrics.annotation.Timed;
import com.janani.twtdw.configurations.twitterConfig.TwitterConfiguration;
import com.janani.twtdw.models.Tweet;
import com.janani.twtdw.services.TwitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import twitter4j.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;


@Component
@Path("/1.0/twitter")
public class TwitterResource{
    private TwitterService tweetMethods;
    private TwitterConfiguration obj;
    private Twitter twitter;
    private static Logger logger =  LoggerFactory.getLogger(TwitterResource.class);

    @Autowired
    public TwitterResource(TwitterService twitterService, TwitterConfiguration twitterConfiguration) throws TwitterException {
        this.obj = twitterConfiguration;
        this.tweetMethods = twitterService;
        twitter = obj.twitterConfig();
    }

    @POST
    @Path("/tweet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response tweetOut(Tweet message) throws TwitterException {
        try {
            Tweet msg = Optional.ofNullable(message).orElse(new Tweet("New tweet-1"));
            String tweetMsg = tweetMethods.postTweet(twitter,msg);
            logger.info("Tweet posted!");
            return Response.status(Response.Status.OK).entity("Successfully posted!").build();
        } catch (TwitterException e) {
            return Response.serverError().entity("Failed to post tweet.").build();
        }
    }


    @GET
    @Path("/timeline")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @Cacheable(cacheNames = "timeline")
    public String getTimeline() throws TwitterException {
        try {
            String  statuses = tweetMethods.getTimeline(twitter);
            logger.info("showing twitter feed.");
            return statuses;
        }catch (TwitterException e) {
            return "Failed to retrieve tweets from timeline.";
        }
    }

    @GET
    @Path("/timeline/filter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @Cacheable(cacheNames = "filter", key ="#keyword.tweets", cacheManager = "alternateCacheManager")
    public List<String> getFilteredTweets(Tweet keyword) throws TwitterException {
        try {
            Tweet search = Optional.ofNullable(keyword).orElse(new Tweet("the"));
            logger.info("showing filtered twitter feed.");
            List<String> statuses = tweetMethods.getFilterTweets(twitter, search);
            return statuses;
        } catch (TwitterException e) {
            return Arrays.asList("Failed to retrieve filtered tweets from timeline.");
        }
    }
}
