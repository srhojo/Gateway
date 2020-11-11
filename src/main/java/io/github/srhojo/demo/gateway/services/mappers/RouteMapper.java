package io.github.srhojo.demo.gateway.services.mappers;

import io.github.srhojo.demo.gateway.api.Route;
import io.github.srhojo.demo.gateway.dao.entities.RouteEntity;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper implements Mapper<RouteEntity, Route> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RouteEntity mapToInner(final Route outer) {
        if(outer==null) {
            return null;
        }
        final RouteEntity inner = new RouteEntity();
        inner.setId(outer.getId());
        inner.setName(outer.getName());
        inner.setHost(outer.getHost());
        inner.setPath(outer.getPath());
        inner.setUri(outer.getUri());
        inner.setHeaders(outer.getHeaders());
        return inner;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Route mapToOuter(final RouteEntity inner) {
        if(inner==null) {
            return null;
        }
        final Route outer =new Route();
        outer.setId(inner.getId());
        outer.setName(inner.getName());
        outer.setHost(inner.getHost());
        outer.setPath(inner.getPath());
        outer.setUri(inner.getUri());
        outer.setHeaders(inner.getHeaders());
        return outer;
    }
}
