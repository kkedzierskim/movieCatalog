package com.example.moviecatalog.services;

import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Set<Movie> getMovies() {
        Set<Movie> movieSet = new HashSet<>();
        movieRepository.findAll().iterator().forEachRemaining(movieSet::add);
        return movieSet;
    }
}
