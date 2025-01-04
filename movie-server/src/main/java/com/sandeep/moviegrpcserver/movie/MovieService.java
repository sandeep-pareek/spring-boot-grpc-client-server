package com.sandeep.moviegrpcserver.movie;

import com.sandeep.moviegrpcserver.movie.exception.MovieNotFoundException;
import com.sandeep.moviegrpcserver.movie.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(int offset, int size) {
        Pageable pageable = PageRequest.of(offset, size);
        return movieRepository.findAll(pageable).getContent();
    }

    public Movie validateAndGetMovieById(String imdbId) {
        return movieRepository.findById(imdbId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id '%s' not found".formatted(imdbId)));
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }
}