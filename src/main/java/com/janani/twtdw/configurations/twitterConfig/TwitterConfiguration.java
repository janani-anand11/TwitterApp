package com.janani.twtdw.configurations.twitterConfig;

import com.janani.twtdw.configurations.twitterConfig.Config;
import io.dropwizard.Configuration;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.InputStream;

@Component
public class TwitterConfiguration extends Configuration {

    public Twitter twitterConfig() throws TwitterException {
        Yaml yaml = new Yaml(new Constructor(Config.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yml");
        Config obj = yaml.load(inputStream);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(obj.getConsumerKey())
                .setOAuthConsumerSecret(obj.getConsumerSecret())
                .setOAuthAccessToken(obj.getAccessToken())
                .setOAuthAccessTokenSecret(obj.getAccessTokenSecret());

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
    }
}
