package io.github.srhojo.demo.gateway.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GatewayApi {

    /**
     * Method to clean all gateway routes
     */
    @DeleteMapping("/gateway/api/routes/clean")
    void cleanRoutes();

    /**
     * Method to refresh all routes from BBDD.
     */
    @PostMapping("/gateway/api/routes/refresh")
    void refreshRoutes();

    /**
     * Method to add new route to BBDD.
     * This method NOT refresh gateway routes, only insert into BBDD
     *
     * @param route route to insert
     * @return {@link Route} saved
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/gateway/api/routes")
    Route addNewRoute(@RequestBody Route route);

    /**
     * Method to update a previous saved route
     * This method NOT refresh gateway routes, only insert into BBDD
     *
     * @param id    route id
     * @param route route data
     * @return {@link Route} saved
     */
    @PutMapping("/gateway/api/routes/{id}")
    Route updateRoute(@PathVariable("id") Long id, @RequestBody Route route);

    /**
     * Method to delete a route
     *
     * @param id route id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/gateway/api/routes/{id}")
    void deleteRoute(@PathVariable("id") Long id);

    /**
     * Method to retrieve all routes from BBDD
     *
     * @return List of {@link Route}
     */
    @GetMapping("/gateway/api/routes")
    List<Route> getAllRoutes();


}
