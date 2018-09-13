package com.webflux.stock.quote.webfluxstockquoteservice.web;

import com.webflux.stock.quote.webfluxstockquoteservice.model.Quote;
import com.webflux.stock.quote.webfluxstockquoteservice.services.QuoteGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@Component
public class QuoteHandler {

    private final QuoteGeneratorService quoteGeneratorService;

    @Autowired
    public QuoteHandler(QuoteGeneratorService quoteGeneratorService) {
        this.quoteGeneratorService = quoteGeneratorService;
    }

    public Mono<ServerResponse> fetchQuotes(ServerRequest serverRequest) {
        int size = Integer.parseInt(serverRequest.queryParam("size").orElse("10"));

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100))
                        .take(size), Quote.class);
    }
}
