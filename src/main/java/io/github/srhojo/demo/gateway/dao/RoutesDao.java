package io.github.srhojo.demo.gateway.dao;

import io.github.srhojo.demo.gateway.dao.entities.RouteEntity;

import java.util.List;

public interface RoutesDao {

    RouteEntity save(RouteEntity route);

    void delete(Long id);

    List<RouteEntity> getAll();

    boolean exist(Long id);
}
