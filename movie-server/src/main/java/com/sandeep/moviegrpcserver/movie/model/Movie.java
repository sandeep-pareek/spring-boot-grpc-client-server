package com.sandeep.moviegrpcserver.movie.model;

import com.sandeep.movieserver.movie.model.MovieProto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movies")
public class Movie {
    public Movie() {
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Id
    private String imdbId;

    private String title;
    private Integer year;
    private Genre genre;

    public Movie(String imdbId, String title, Integer year, Genre genre) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public static Movie from(MovieProto.CreateMovieRequest request) {
        Genre genre = Genre.valueOf(request.getGenre().name());
        return new Movie(request.getImdbId(), request.getTitle(), request.getYear(), genre);
    }

    public static void updateFrom(MovieProto.UpdateMovieRequest request, Movie movie) {
        if (!request.getTitle().isEmpty()) {
            movie.setTitle(request.getTitle());
        }
        if (request.getYear() != 0) {
            movie.setYear(request.getYear());
        }
        if (request.getGenreValue() >= 0) {
            movie.setGenre(Genre.valueOf(request.getGenre().name()));
        }
    }

    public MovieProto.Movie toProto() {
        return MovieProto.Movie.newBuilder()
                .setImdbId(this.getImdbId())
                .setTitle(this.getTitle())
                .setYear(this.getYear())
                .setGenre(MovieProto.Genre.valueOf(this.getGenre().name()))
                .build();
    }
}