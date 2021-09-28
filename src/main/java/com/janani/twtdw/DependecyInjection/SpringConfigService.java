package com.janani.twtdw.DependecyInjection;

import com.janani.twtdw.services.TwitterInterfaceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.janani.twtdw.services")
public class SpringConfigService {
    @Bean
    public TwitterInterfaceImpl twitterInterface(){
        return new TwitterInterfaceImpl();
    }
}
