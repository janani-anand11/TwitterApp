package com.janani.twtdw.resources;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/api/1.0/twitter")
public class TwitterResource{
    private Twitter twitter;
    private List<Status> statuses;
    private static org.slf4j.Logger logger =  LoggerFactory.getLogger(TwitterResource.class);

    public TwitterResource() throws TwitterException {
        twitter = TwitterFactory.getSingleton();
        statuses = new ArrayList<Status>();
    }

    @POST
    @Path("/tweet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response tweetOut(Tweet message) throws TwitterException {
        try {
            twitter.updateStatus(message.getTweets());
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
            statuses = twitter.getHomeTimeline();
            ArrayList<String> temp = new ArrayList<>();
            for (Status status : statuses) {
                temp.add(status.getUser().getName()+": "+(status.getText()));
            }
            logger.info("showing twitter feed.");
            return Response.status(Response.Status.OK).entity(temp.toString()).build();
        } catch (TwitterException e) {
            return Response.serverError().entity("Failed to retrieve tweets from timeline.").build();
        }
    }
}
