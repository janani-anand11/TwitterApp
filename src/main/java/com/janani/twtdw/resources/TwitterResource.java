package com.janani.twtdw.resources;

import com.codahale.metrics.annotation.Timed;
import com.janani.twtdw.configurations.TwitterConfiguration;
import com.janani.twtdw.models.Tweet;
import com.janani.twtdw.models.TwitterGetUserInfo;
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
@Path("/api/1.0/twitter")
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
    public Response getTimeline() throws TwitterException {
        try {
            List<TwitterGetUserInfo> statuses = tweetMethods.getTimeline(twitter);
            logger.info("showing twitter feed.");
            return Response.status(Response.Status.OK).entity(statuses).build();
        } catch (TwitterException e) {
            return Response.serverError().entity("Failed to retrieve tweets from timeline.").build();
        }
    }

    @GET
    @Path("/timeline/filter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response getFilteredTweets(Tweet keyword) throws TwitterException {
        try {
            Tweet search = Optional.ofNullable(keyword).orElse(new Tweet("the"));
            List<String> statuses = tweetMethods.getFilterTweets(twitter, search);
            return Response.status(Response.Status.OK).entity(statuses).build();
        } catch (TwitterException e) {
            return Response.serverError().entity("Failed to retrieve filtered tweets from timeline.").build();
        }
    }
}
