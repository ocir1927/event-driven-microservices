package com.disertatie.notification.service;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfiguration {

//    @Autowired
//    public void configureInMemoryTokenStore(EventProcessingConfigurer configurer) {
//        configurer.registerTokenStore(configuration -> new InMemoryTokenStore());
//    }
}
