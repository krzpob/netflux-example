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
        movieRepository.deleteAll().thenMany( // <0>
                Flux.just("Silence of the Lambdas", "AEon Flux", "Enter the Mono<Void>", "The Fluxxinator",
                        "Back to the feature", "Meet the Fluxes", "Lord of the fluxes")// <1>
                        .map(title -> new Movie(UUID.randomUUID().toString(), title)) //<2>
                        .flatMap(movieRepository::save)  // <3>
        ).subscribe(null, null, () -> {        // <4>
            movieRepository.findAll().subscribe(System.out::println);
        });
    }
}
