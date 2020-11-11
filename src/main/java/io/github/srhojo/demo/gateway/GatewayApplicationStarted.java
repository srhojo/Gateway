package io.github.srhojo.demo.gateway;

import io.github.srhojo.demo.gateway.configuration.UriConfiguration;
import io.github.srhojo.demo.gateway.services.impl.RoutesServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(UriConfiguration.class)
public class GatewayApplicationStarted {

    private final RoutesServiceImpl routesService;

    public GatewayApplicationStarted(final RoutesServiceImpl routesService) {
        this.routesService = routesService;
    }

    public void init() {
        routesService.refreshDataBaseRoutes();
    }


}
