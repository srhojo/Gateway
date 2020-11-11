package io.github.srhojo.demo.gateway.dao.repositories;

import io.github.srhojo.demo.gateway.dao.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoutesRepository extends JpaRepository<RouteEntity,Long> {
}
