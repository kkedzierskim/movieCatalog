package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.converters.MovieCommandToMovie;
import com.example.moviecatalog.converters.MovieToMovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.exceptions.NotFoundException;
import com.example.moviecatalog.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieCommandToMovie movieCommandToMovie;
    private final MovieToMovieCommand movieToMovieCommand;

    public MovieServiceImpl(MovieRepository movieRepository, MovieCommandToMovie movieCommandToMovie, MovieToMovieCommand movieToMovieCommand) {
        this.movieRepository = movieRepository;
        this.movieCommandToMovie = movieCommandToMovie;
        this.movieToMovieCommand = movieToMovieCommand;
    }


    @Override
    public Set<Movie> getMovies() {
        Set<Movie> movieSet = new HashSet<>();
        movieRepository.findAll().iterator().forEachRemaining(movieSet::add);
        return movieSet;
    }

    @Override
    public Movie getMovieById(Long id) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (!optionalMovie.isPresent()) {
            throw new NotFoundException("Movie not found with id: " + id);
        }
        return optionalMovie.get();
    }

    @Override
    @Transactional
    public MovieCommand saveMovieCommand(MovieCommand command) {
        Movie detachedMovie = movieCommandToMovie.convert(command);
        Movie savedMovie = movieRepository.save(detachedMovie);
        log.debug("saved Movie with id" + savedMovie.getId());
        return movieToMovieCommand.convert(savedMovie);

    }

    @Override
    @Transactional
    public MovieCommand getMovieCommandById(Long id) {
        return movieToMovieCommand.convert(getMovieById(id));
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Set<Movie> findMovieByTitle(String title) {
        Set<Movie> movieSet = new HashSet<>();
        movieRepository.findAllByTitleIgnoreCase(title).iterator().forEachRemaining(movieSet::add);
        return movieSet;
    }
}