package com.amani.test.jersey.async.resource;

import com.amani.test.jersey.async.service.JerseyAsyncService;
import com.amani.test.jersey.async.service.impl.JerseyAsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Component
@Named
@Path(value = "/")
public class JerseyAsyncResource {

    @Autowired
    private Environment env;

    @Resource(name = "JerseyAsyncService")
    private JerseyAsyncService jerseyAsyncService;

    @GET
    @Path(value = "test-parallel-calls")
    public Response getValue() throws Throwable{

        String result = jerseyAsyncService.runWorkflow();
        Response.Status status = Response.Status.OK;

        return Response.status(status).build();
    }



}
