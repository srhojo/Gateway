package io.github.srhojo.demo.gateway.services;

import io.github.srhojo.demo.gateway.api.Route;

import java.util.List;

public interface RoutesService {

    void refreshDataBaseRoutes();

    void cleanRoutes();

    Route addNewRoute(Route route);

    Route updateRoute(Route route);

    List<Route> getAllRoutes();

    void deleteRoute(Long id);

}
