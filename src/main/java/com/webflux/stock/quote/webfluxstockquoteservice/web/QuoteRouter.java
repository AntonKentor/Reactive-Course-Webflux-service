package com.webflux.stock.quote.webfluxstockquoteservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class QuoteRouter {

    @Bean
    public RouterFunction<ServerResponse> rout(QuoteHandler handler) {

        // Whenever we access "/quotes" and the MediaType of application_json, we will invoke fetchquotes - handler.

        return RouterFunctions.route(GET("/quotes")
                .and(accept(MediaType.APPLICATION_JSON)), handler::fetchQuotes);
    }
}
