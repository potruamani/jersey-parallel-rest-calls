package com.amani.test.jersey.async.service;

import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public interface HttpServiceAgent<ServiceRequest, ServiceResponse> {
    ServiceResponse consume(@NotNull ServiceRequest request);

    default Invocation.Builder initializeWebTarget(WebTarget target) {
        return target.request(MediaType.APPLICATION_JSON);
    }
}
