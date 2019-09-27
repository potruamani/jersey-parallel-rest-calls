package com.amani.test.jersey.async.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Component
public class SpringConfig {

    @Bean("insecureHttpClient")
    public Client getHttpClient() {
        return ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

}
