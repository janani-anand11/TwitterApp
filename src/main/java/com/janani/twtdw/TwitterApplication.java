package com.janani.twtdw;

import com.janani.twtdw.configurations.DependecyInjection.SpringConfig;
import com.janani.twtdw.configurations.DependecyInjection.SpringConfigService;
import com.janani.twtdw.configurations.caching.CacheConfig;
import com.janani.twtdw.configurations.twitterConfig.TwitterConfiguration;
import com.janani.twtdw.resources.TwitterResource;
import com.janani.twtdw.services.TwitterService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import twitter4j.TwitterException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
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

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }
}