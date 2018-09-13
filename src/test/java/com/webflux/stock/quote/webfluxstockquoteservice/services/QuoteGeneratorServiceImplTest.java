package com.webflux.stock.quote.webfluxstockquoteservice.services;

import com.webflux.stock.quote.webfluxstockquoteservice.model.Quote;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class QuoteGeneratorServiceImplTest {

    QuoteGeneratorService quoteGeneratorService = new QuoteGeneratorServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void fetchQuoteStream() throws Exception {

        //This test will terminate before anything will be printed to console.

        //get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(1L));

        quoteFlux.take(1000000).subscribe(System.out::println);

        //Dont use thread sleep. Use CountdownLatch instead.
        Thread.sleep(1000);
    }

    @Test
    public void fetchQuoteStreamCountDown() throws InterruptedException {

        //get quoteFlux of quotes
        Flux<Quote> quoteFlux = quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100L));

        //Subscriber Lambda
        Consumer<Quote> println = System.out::println;

        //Error handler
        Consumer<Throwable> errorHandler = e -> System.out.println("Some error occurred");

        //set Countdown latch to 1
        //Is used to ask the program to pause and wait. Number one refers to paus 1 time.
        //Is used instead of thread sleep.
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //Runnable called upon complete, count down latch
        Runnable allDone = () -> countDownLatch.countDown();

        quoteFlux.take(10)
                .subscribe(println, errorHandler, allDone);

        countDownLatch.await();
    }

}