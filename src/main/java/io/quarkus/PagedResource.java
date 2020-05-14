package io.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Page;

@Path("/")
public final class PagedResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Page hello(@Context Page page) {
        return page;
    }
}