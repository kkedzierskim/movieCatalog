package com.example.moviecatalog.services;


import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.converters.ActorToActorCommand;
import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.ActorRepository;
import com.example.moviecatalog.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ActorServiceImpl implements ActorService {

    MovieRepository movieRepository;
    ActorToActorCommand actorToActorCommand;

    public ActorServiceImpl(MovieRepository movieRepository, ActorToActorCommand actorToActorCommand) {
        this.movieRepository = movieRepository;
        this.actorToActorCommand = actorToActorCommand;
    }

    @Override
    public ActorCommand findActorByMovieIdAndActorId(Long movieId, Long actorId) {

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (!optionalMovie.isPresent()) {
            //todo impl error handling
            log.error("movie id not found");
        }
        Movie movie = optionalMovie.get();

        Optional<ActorCommand> actorCommandOptional = movie.getActors().stream()
                .filter(actor -> actor.getId().equals(actorId))
                .map(actor -> actorToActorCommand.convert(actor))
                .findFirst();

        if(!actorCommandOptional.isPresent()){
            //todo impl error handling
            log.error("actor id not found");
        }
        return actorCommandOptional.get();
    }
}
