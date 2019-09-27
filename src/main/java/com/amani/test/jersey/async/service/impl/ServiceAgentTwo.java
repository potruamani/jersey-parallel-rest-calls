package com.amani.test.jersey.async.service.impl;

import com.amani.test.jersey.async.service.HttpServiceAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;

@Component
public class ServiceAgentTwo implements HttpServiceAgent<String, CompletableFuture> {

    private WebTarget webTarget;

    @Autowired
    ServiceAgentTwo(@Qualifier("insecureHttpClient") Client serviceOneClient) {
        this.webTarget = serviceOneClient.target("http://graphlll.facebook.com/test");
    }
    @Override
    public CompletableFuture consume(@NotNull String s) {
        final CompletableFuture completableFuture = new CompletableFuture();
        try {
            Invocation.Builder builder = initializeWebTarget(webTarget);
            builder.async().get(new InvocationCallback<Response>() {

                @Override
                public void completed(Response response) {
                    completableFuture.complete(response);
                }

                @Override
                public void failed(Throwable throwable) {
                    System.out.println("exception in ServiceAgentTwoAsyncClient"+ throwable.getMessage()+ throwable.getCause());
                    completableFuture.completeExceptionally(throwable.getCause());
                }
            });
        } catch (Exception ex) {
            System.out.println("exception in ServiceAgentTwo"+ ex.getMessage()+ ex.getCause());
        }
        return completableFuture;
    }
}
