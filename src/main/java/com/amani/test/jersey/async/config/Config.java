package com.amani.test.jersey.async.config;


import com.amani.test.jersey.async.resource.JerseyAsyncResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Config extends ResourceConfig {
    public Config() {
        super();
        register(JerseyAsyncResource.class);
    }
}
