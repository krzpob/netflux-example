package guru.springframework.netfluxexample.service;

import java.time.Duration;
import java.util.Date;

import guru.springframework.netfluxexample.domain.Movie;
import guru.springframework.netfluxexample.domain.MovieEvent;
import guru.springframework.netfluxexample.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Flux<MovieEvent> events(String movieId) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
            movieEventSynchronousSink.next(new MovieEvent(movieId, new Date()));
        }).delayElements(Duration.ofSeconds(1)); // <1>
    }

    @Override
    public Mono<Movie> getMoviebyId(String id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }
}
