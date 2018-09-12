package com.webflux.stock.quote.webfluxstockquoteservice.services;

import com.webflux.stock.quote.webfluxstockquoteservice.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    public Flux<Quote> fetchQuoteStream(Duration duration);

}
