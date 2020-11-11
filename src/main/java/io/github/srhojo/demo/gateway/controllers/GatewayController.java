package io.github.srhojo.demo.gateway.controllers;

import io.github.srhojo.demo.gateway.api.GatewayApi;
import io.github.srhojo.demo.gateway.api.Route;
import io.github.srhojo.demo.gateway.services.RoutesService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GatewayController implements GatewayApi {

    private final RoutesService routesService;

    public GatewayController(final RoutesService routesService) {
        this.routesService = routesService;
    }

    public void cleanRoutes() {
        routesService.cleanRoutes();
    }

    @Override
    public void refreshRoutes() {
        routesService.refreshDataBaseRoutes();
    }

    @Override
    public Route addNewRoute(@RequestBody @Valid final Route route) {
        route.setId(null);
        return routesService.addNewRoute(route);
    }

    @Override
    public Route updateRoute(@PathVariable("id") final Long id,  @RequestBody @Valid final Route route) {
        route.setId(id);
        return routesService.updateRoute(route);
    }

    @Override
    public void deleteRoute(@PathVariable("id") Long id) {
        routesService.deleteRoute(id);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routesService.getAllRoutes();
    }


}
