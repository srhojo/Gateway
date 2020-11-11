package io.github.srhojo.demo.gateway.services.impl;

import io.github.srhojo.demo.gateway.api.Route;
import io.github.srhojo.demo.gateway.dao.RoutesDao;
import io.github.srhojo.demo.gateway.dao.entities.RouteEntity;
import io.github.srhojo.demo.gateway.routing.RefreshableRouteLocator;
import io.github.srhojo.demo.gateway.services.RoutesService;
import io.github.srhojo.demo.gateway.services.mappers.RouteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutesServiceImpl implements RoutesService {

    private final RouteMapper routeMapper;
    private final RoutesDao routesDao;
    private final RefreshableRouteLocator refreshableRouteLocator;

    public RoutesServiceImpl(final RouteMapper routeMapper, final RoutesDao routesDao, final RefreshableRouteLocator refreshableRouteLocator) {
        this.routeMapper = routeMapper;
        this.routesDao = routesDao;
        this.refreshableRouteLocator = refreshableRouteLocator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshDataBaseRoutes() {
        final List<RouteEntity> routes = routesDao.getAll();
        refreshableRouteLocator.cleanRoutes();
        refreshableRouteLocator.addRoutes(routes).buildRoutes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanRoutes() {
        refreshableRouteLocator.cleanRoutes();
        refreshableRouteLocator.buildRoutes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Route addNewRoute(final Route route) {
        final RouteEntity routeEntity = routeMapper.mapToInner(route);
        final RouteEntity savedRouteEntity = routesDao.save(routeEntity);
        return routeMapper.mapToOuter(savedRouteEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Route updateRoute(final Route route) {
        final RouteEntity routeEntity = routeMapper.mapToInner(route);
        //TODO: check if exist the previous entity
        final RouteEntity savedEntity = routesDao.save(routeEntity);
        return routeMapper.mapToOuter(savedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Route> getAllRoutes() {
        final List<RouteEntity> routes = routesDao.getAll();
        return routeMapper.toOuter(routes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRoute(final Long id) {
        routesDao.delete(id);
    }

}
