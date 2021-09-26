package com.janani.twtdw;

import com.janani.twtdw.configurations.TwitterConfiguration;
import com.janani.twtdw.resources.TwitterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import twitter4j.TwitterException;

public class TwitterApplication extends Application<TwitterConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public String getName() {
        return "TwitterApp";
    }

    @Override
    public void initialize(final Bootstrap<TwitterConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TwitterConfiguration configuration,
                    final Environment environment) throws TwitterException {
        final TwitterResource resources = new TwitterResource();
        environment.jersey()
                .register(resources);
    }

}