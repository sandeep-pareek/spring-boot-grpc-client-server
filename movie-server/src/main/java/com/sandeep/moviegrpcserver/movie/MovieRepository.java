package com.sandeep.moviegrpcserver.movie;

import com.sandeep.moviegrpcserver.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}