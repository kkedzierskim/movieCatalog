package com.example.moviecatalog.services;


import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.converters.ActorCommandToActor;
import com.example.moviecatalog.converters.ActorToActorCommand;
import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.ActorRepository;
import com.example.moviecatalog.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ActorServiceImpl implements ActorService {

    MovieRepository movieRepository;
    ActorToActorCommand actorToActorCommand;
    ActorCommandToActor actorCommandToActor;
    ActorRepository actorRepository;

    public ActorServiceImpl(MovieRepository movieRepository, ActorToActorCommand actorToActorCommand,
                            ActorCommandToActor actorCommandToActor, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorToActorCommand = actorToActorCommand;
        this.actorCommandToActor = actorCommandToActor;
        this.actorRepository = actorRepository;
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

        if (!actorCommandOptional.isPresent()) {
            //todo impl error handling
            log.error("actor id not found");
        }
        return actorCommandOptional.get();
    }


    @Transactional
    @Override
    public ActorCommand saveOrUpdateActorCommand(ActorCommand command) {


        Optional<Movie> movieOptional = movieRepository.findById(command.getMovieCommandId());

        if (!movieOptional.isPresent()) {
            log.error("movie not found");
            return new ActorCommand();
        } else {
            Movie movie = movieOptional.get();

            Optional<Actor> actorOptional = movie
                    .getActors()
                    .stream()
                    .filter(actor -> actor.getId().equals(command.getId()))
                    .findFirst();

            if (actorOptional.isPresent()) {
                Actor actorFound = actorOptional.get();
                actorFound.setFirstName(command.getFirstName());
                actorFound.setLastName(command.getLastName());
                actorFound.setFilmName(command.getFilmName());

            } else {
                Actor newActor = actorCommandToActor.convert(command);
                newActor.setMovie(movie);
                movie.addActor(newActor);
            }

            Movie savedMovie = movieRepository.save(movie);

            Optional<Actor> savedActorOptional = savedMovie.getActors().stream()
                    .filter(movieActor -> movieActor.getId().equals(command.getId()))
                    .findFirst();


            if (!savedActorOptional.isPresent()) {
                savedActorOptional = savedMovie.getActors().stream()
                        .filter(movieActor -> movieActor.getFirstName().equals(command.getFirstName()))
                        .filter(movieActor -> movieActor.getLastName().equals(command.getLastName()))
                        .filter(movieActor -> movieActor.getFilmName().equals(command.getFilmName()))
                        .findFirst();
            }

            //to do check for fail
            return actorToActorCommand.convert(savedActorOptional.get());
        }
    }

    @Transactional
    @Override
    public void deleteById(Long movieId, Long idToDelete) {

        log.debug("Deleting actor: " + idToDelete);

        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            log.debug("found movie");

            Optional<Actor> actorOptional = movie
                    .getActors()
                    .stream()
                    .filter(actor -> actor.getId().equals(idToDelete))
                    .findFirst();

            if (actorOptional.isPresent()) {
                log.debug("found actor");
                Actor actorToDelete = actorOptional.get();
                actorToDelete.setMovie(null);
                movie.getActors().remove(actorOptional.get());
                movieRepository.save(movie);
                actorRepository.deleteById(idToDelete);
            }
        } else {
            log.debug("movie Id Not found. Id:" + movieId);
        }
    }
}


