package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;

import java.util.List;
import java.util.Set;

public interface MovieService {

   Set<Movie> getMovies();

   Movie getMovieById(Long id);

   MovieCommand saveMovieCommand(MovieCommand command);
}
