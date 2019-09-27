package com.amani.test.jersey.async.service.impl;

import com.amani.test.jersey.async.service.JerseyAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service("JerseyAsyncService")
public class JerseyAsyncServiceImpl implements JerseyAsyncService {

    private ServiceAgentOne serviceAgentOne ;
    private ServiceAgentTwo serviceAgentTwo;


    @Autowired
    public JerseyAsyncServiceImpl(ServiceAgentOne serviceAgentOne, ServiceAgentTwo serviceAgentTwo) {
        this.serviceAgentOne = serviceAgentOne;
        this.serviceAgentTwo = serviceAgentTwo;
    }
    @Override
    public String runWorkflow() {
        CompletableFuture completableServiceAgentOneResponse = serviceAgentOne.consume("testServiceAgentOne");
        CompletableFuture completableServiceAgentTwoResponse = serviceAgentTwo.consume("testServiceAgentTwo");
        try {
            CompletableFuture.allOf(completableServiceAgentOneResponse, completableServiceAgentTwoResponse);
            System.out.println("completableServiceAgentOneResponse:  " + completableServiceAgentOneResponse.get());
            System.out.println("completableServiceAgentTwoResponse:  " + completableServiceAgentTwoResponse.get(3000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            System.out.println(e.fillInStackTrace());
        }
        return "testCompleted";
    }
}
