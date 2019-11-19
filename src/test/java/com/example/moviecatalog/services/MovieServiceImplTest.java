package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.converters.MovieCommandToMovie;
import com.example.moviecatalog.converters.MovieToMovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


class MovieServiceImplTest {

    MovieServiceImpl movieService;

    @Mock
    MovieRepository movieRepository;

    @Mock
    MovieToMovieCommand movieToMovieCommand;

    @Mock
    MovieCommandToMovie movieCommandToMovie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository, movieCommandToMovie, movieToMovieCommand);
    }

    @Test
    void getMoviesTest() {
        //given
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Set<Movie> moviesData = new HashSet<>();
        moviesData.add(movie1);
        moviesData.add(movie2);
        //when
        when(movieService.getMovies()).thenReturn(moviesData);
        Set<Movie> movies = movieService.getMovies();
        //then
        assertEquals(movies.size(), 2);
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void getMovieByIdTest() {
        //given
        Movie movieData = new Movie();
        movieData.setId(3L);

        //when
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieData));
        Movie movie = movieService.getMovieById(3L);

        //then
        assertEquals(movie.getId(), 3L);
        verify(movieRepository,times(1)).findById(anyLong());
    }

    @Test
    void findCommandByIdTest() {
        Movie movie = new Movie();
        movie.setId(1L);
        Optional<Movie> movieOptional = Optional.of(movie);

        when(movieRepository.findById(anyLong())).thenReturn(movieOptional);

        MovieCommand movieCommand = new MovieCommand();
        movieCommand.setId(1L);

        when(movieToMovieCommand.convert(any())).thenReturn(movieCommand);

        MovieCommand commandById = movieService.getMovieCommandById(1L);

        assertNotNull("not null", commandById);
        verify(movieRepository, times(1)).findById(anyLong());
        verify(movieRepository, never()).findAll();

    }

    @Test
    void deleteMovie() {
        //given
        Movie movie = new Movie();
        movie.setId(4L);
        //when
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        Long idToDelete = movieService.getMovieById(anyLong()).getId();
        movieService.deleteMovieById(idToDelete);
        //then
        verify(movieRepository,times(1)).deleteById(anyLong());
        verify(movieRepository,times(1)).findById(anyLong());
        assertEquals(idToDelete, 4L);
    }
}