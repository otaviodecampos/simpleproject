package com.simpleproject.resource;

import com.simpleproject.service.ServiceTest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by otavio on 09/05/2017.
 */
@Path("test")
public class TestResource {

    @Inject
    private ServiceTest service;

    @GET
    public String test() {
        return "test";
    }

}
