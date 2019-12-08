package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Genre;
import com.example.moviecatalog.domain.Movie;
import java.util.Set;

public interface MovieService {

   Set<Movie> getMovies();

   Set<Movie> findMovieByTitle(String title);

   Set<Movie> findMovieByGenre(Genre genre);

   Movie getMovieById(Long id);

   MovieCommand saveMovieCommand(MovieCommand command);

   MovieCommand getMovieCommandById(Long id);

   void deleteMovieById(Long id);
}
