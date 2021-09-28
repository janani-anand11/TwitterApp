package com.janani.twtdw.DependecyInjection;

import com.janani.twtdw.configurations.TwitterConfiguration;
import com.janani.twtdw.services.TwitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.janani.twtdw.resources")
public class SpringConfig {
    @Bean
    public TwitterService twitterService(){
        return new TwitterService();
    }

    @Bean
    public TwitterConfiguration twitterConfiguration(){
        return new TwitterConfiguration();
    }
}
