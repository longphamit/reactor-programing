package com.longpc.reactorprograming.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {
    @Test
    public void fluxText(){
        Flux<String> stringFlux= Flux.just("Srping","Spring Boot","Reactive Spring")
                .concatWith(Flux.just("After Error"))
                        .log();
        stringFlux.subscribe(
                System.out::println,
                (e)->System.out.println(e),
                ()->System.out.println("Complete")
        );

    }
    @Test
    public void fluxTestElements_WithoutError(){
        Flux<String> stringFlux= Flux.just("Spring","Spring Boot","Reactive Spring")
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring Boot")
                .expectNext("Spring")
                .expectNext("Reactive Spring");
                //.verifyComplete();

    }
}
