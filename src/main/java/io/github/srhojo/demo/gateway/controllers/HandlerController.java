package io.github.srhojo.demo.gateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HandlerController {

    @GetMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }


}
