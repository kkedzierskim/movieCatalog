package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.converters.MovieCommandToMovie;
import com.example.moviecatalog.converters.MovieToMovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MovieServiceIT {
    public static final String NEW_TITLE = "New Title";

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieCommandToMovie movieCommandToMovie;

    @Autowired
    MovieToMovieCommand movieToMovieCommand;

    @Transactional
    @Test
    void testTitleChangeOfMovieCommand() {
        //given
        Iterable<Movie> movies = movieRepository.findAll();
        Movie testMovie = movies.iterator().next();
        MovieCommand testMovieCommand = movieToMovieCommand.convert(testMovie);

        //when
        testMovieCommand.setTitle(NEW_TITLE);
        MovieCommand savedMovieCommand = movieService.saveMovieCommand(testMovieCommand);

        //then
        assertEquals(NEW_TITLE, savedMovieCommand.getTitle());
        assertEquals(testMovie.getId(), savedMovieCommand.getId());
        assertEquals(testMovie.getActors().size(), savedMovieCommand.getActors().size());
    }
}