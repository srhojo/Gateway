package io.github.srhojo.demo.gateway.routing;

import io.github.srhojo.demo.gateway.configuration.UriConfiguration;
import io.github.srhojo.demo.gateway.dao.entities.RouteEntity;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Component
@EnableConfigurationProperties(UriConfiguration.class)
public class RefreshableRouteLocator implements RouteLocator {

    private final RouteLocatorBuilder builder;
    private final GatewayRouterRefresher gatewayRouterRefresher;

    private RouteLocatorBuilder.Builder routesBuilder;
    private Flux<Route> routes = Flux.empty();

    public RefreshableRouteLocator(final RouteLocatorBuilder builder, final GatewayRouterRefresher gatewayRouterRefresher) {
        this.builder = builder;
        this.gatewayRouterRefresher = gatewayRouterRefresher;

        this.cleanRoutes();
    }


    public RefreshableRouteLocator addRoutes(List<RouteEntity> routesModel) {
        routesModel.forEach(this::createRoute);
        return this;
    }

    public RefreshableRouteLocator addRoute(RouteEntity routeModel) {
        createRoute(routeModel);
        return this;
    }

    public void cleanRoutes() {
        routesBuilder = builder.routes();
    }

    public void buildRoutes() {
        this.routes = routesBuilder.build().getRoutes();
        gatewayRouterRefresher.refreshRoutes();
    }

    @Override
    public Flux<Route> getRoutes() {
        return routes;
    }

    private void createRoute(final RouteEntity routeModel) {
        if (routeModel.getHost() != null) {
            routesBuilder.route(routeModel.getName(), r -> r
                    .host(routeModel.getHost())
                    .filters(f -> createRouteFilter(f, routeModel.getHeaders(), true))
                    .uri(routeModel.getUri()));
        } else {
            routesBuilder.route(routeModel.getName(), r -> r
                    .path(routeModel.getPath())
                    .filters(f -> createRouteFilter(f, routeModel.getHeaders(), false))
                    .uri(routeModel.getUri()));
        }
    }

    private UriSpec createRouteFilter(@NonNull final GatewayFilterSpec filterSpec, final Map<String, String> headers, boolean needFallback) {
        filterSpec.retry(retryConfig -> {
            retryConfig.setRetries(3);
            retryConfig.setStatuses(HttpStatus.INTERNAL_SERVER_ERROR);
            retryConfig.setMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
        });

        //Añadir cabeceras si hacen falta
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(filterSpec::addRequestHeader);
        }

        //Método de fallback genérico
        if (needFallback) {
            filterSpec.hystrix(config -> config
                    .setName("myCmd")
                    .setFallbackUri("forward:/fallback"));
        }
        return filterSpec;
    }
}
