package io.quarkus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.ContextInjector;

import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;

@Provider
public class PageInjector implements ContextInjector<Uni<Page>, Page> {

    @Context
    UriInfo uriInfo;

    @Override
    public Uni<Page> resolve(Class<? extends Uni<Page>> rawType, Type genericType, Annotation[] annotations) {
        return Uni.createFrom().item(Page.of(getIndex(), getSize()));
    }

    private int getIndex() {
        // TODO Check application properties and do proper validation
        MultivaluedMap<String, String> params = uriInfo.getQueryParameters(true);
        if (params.containsKey("page")) {
            return Integer.parseInt(params.getFirst("page"));
        }
        return 0;
    }

    private int getSize() {
        // TODO Check application properties and do proper validation
        MultivaluedMap<String, String> params = uriInfo.getQueryParameters(true);
        if (params.containsKey("size")) {
            return Integer.parseInt(params.getFirst("size"));
        }
        return 20;
    }
}
