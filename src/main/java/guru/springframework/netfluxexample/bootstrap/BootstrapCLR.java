package guru.springframework.netfluxexample.bootstrap;

import java.util.UUID;

import guru.springframework.netfluxexample.domain.Movie;
import guru.springframework.netfluxexample.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class BootstrapCLR implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll().thenMany( // <1>
                Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>",
                        "The Fluxxinator", "Back to the feature", "Meet the Fluxes",
                        "Lord of the fluxes")// <2>
                        .map(Movie::new) //<3>
                        .flatMap(movieRepository::save)  // <4>
        ).subscribe(null, null, () -> {        // <5>
            movieRepository.findAll().subscribe(System.out::println);
        });
    }
}
