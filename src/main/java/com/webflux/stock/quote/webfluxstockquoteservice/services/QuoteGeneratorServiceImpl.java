package com.webflux.stock.quote.webfluxstockquoteservice.services;

import com.webflux.stock.quote.webfluxstockquoteservice.model.Quote;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class QuoteGeneratorServiceImpl implements QuoteGeneratorService {
    @Override
    public Flux<Quote> fetchQuoteStream(Duration duration) {
        return null;
    }
}
