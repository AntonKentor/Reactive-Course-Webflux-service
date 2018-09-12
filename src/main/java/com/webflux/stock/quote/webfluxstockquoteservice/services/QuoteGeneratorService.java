package com.webflux.stock.quote.webfluxstockquoteservice.services;

import com.webflux.stock.quote.webfluxstockquoteservice.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration duration);

}
