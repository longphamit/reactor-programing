package com.longpc.reactorprograming.apis;

import com.longpc.reactorprograming.dtos.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServlet;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class FluxAndMonoAPI {
    @GetMapping(value = "flux", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person returnFlux() {
        Mono<String> fluxName = getNameBy3Seconds().next(); // mất 3 giây để lấy name
        Mono<String> fluxSchool = getSchoolBy3Seconds().next(); // mất 3 giây để lấy school
        fluxName = fluxName.map(e -> e.toUpperCase());
        fluxSchool = fluxSchool.map(e -> e.toUpperCase());
        Mono<Person> rs = Mono.zip(fluxName, fluxSchool, (name, school) -> new Person(name, school));
        return rs.block();
    }

//    @GetMapping(value = "flu2x", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Person> returnFlux2() {
//        List<Mono<Person>> listMono = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            listMono.add(getNameBy3Seconds().next()); // mỗi lần call mất 3 giây
//        }
//        Mono<List<Person>> rs = Mono.zip(listMono, e -> Arrays.stream(e)
//                .map(a -> {
//
//                    return new Person(a.toString(), "FPT");
//                })
//                .collect(Collectors.toList()));
//        // mất 3 giây để lấy name
//        return rs.block();
//    }

    public Flux<String> getNameBy3Seconds() {
        return Flux.just("abc").delayElements(Duration.ofSeconds(3)).log();
    }

    public Flux<String> getSchoolBy3Seconds() {
        return Flux.just("FPT").delayElements(Duration.ofSeconds(3)).log();
    }
}
