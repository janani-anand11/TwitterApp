package com.janani.twtdw;

import com.janani.twtdw.configurations.DependecyInjection.SpringConfig;
import com.janani.twtdw.configurations.DependecyInjection.SpringConfigService;
import com.janani.twtdw.configurations.caching.CacheConfig;
import com.janani.twtdw.configurations.twitterConfig.TwitterConfiguration;
import com.janani.twtdw.resources.TwitterResource;
import com.janani.twtdw.services.TwitterService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
    }

    @Override
    public void run(final TwitterConfiguration configuration,
                    final Environment environment) throws TwitterException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfig.class, SpringConfigService.class);
        ctx.register(CacheConfig.class);
        ctx.refresh();
        TwitterService twitterService = ctx.getBean(TwitterService.class);
        TwitterResource resources = ctx.getBean(TwitterResource.class);
        environment.jersey()
                .register(resources);
    }
}