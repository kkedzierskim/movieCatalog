package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.converters.ActorCommandToActor;
import com.example.moviecatalog.converters.ActorToActorCommand;
import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.ActorRepository;
import com.example.moviecatalog.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ActorServiceImplTest {

    private ActorService actorService;
    private ActorToActorCommand actorToActorCommand;
    private ActorCommandToActor actorCommandToActor;


    @Mock
    MovieRepository movieRepository;

    @Mock
    ActorRepository actorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        actorCommandToActor = new ActorCommandToActor();
        actorToActorCommand = new ActorToActorCommand();
        actorService = new ActorServiceImpl(movieRepository, actorToActorCommand, actorCommandToActor, actorRepository);
    }

    @Test
    void findActorByMovieIdAndActorId() {
        //given
        Movie movie = new Movie();
        movie.setId(1L);
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        Actor actor3 = new Actor();
        actor1.setId(1L);
        actor2.setId(2L);
        actor3.setId(3L);
        actor1.setMovie(movie);
        actor2.setMovie(movie);
        actor3.setMovie(movie);
        movie.addActor(actor1);
        movie.addActor(actor2);
        movie.addActor(actor3);

        Optional<Movie> movieOptional = Optional.of(movie);

        //when
        when(movieRepository.findById(anyLong())).thenReturn(movieOptional);

        //then
        ActorCommand actorCommand = actorService.findActorByMovieIdAndActorId(1L, 3L);
        assertEquals(3L, actorCommand.getId());
        assertEquals(1L, actorCommand.getMovieCommandId());
        verify(movieRepository, times(1)).findById(anyLong());


    }

    @Test
    void deleteById() {

        Movie movie = new Movie();
        movie.setId(1L);
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        actor1.setId(1L);
        actor2.setId(2L);
        movie.addActor(actor1);
        movie.addActor(actor2);

        //when
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        when(movieRepository.save(any())).thenReturn(movie);

        actorService.deleteById(1L, 2L);
        assertEquals(movie.getActors().size(), 1);


    }

    @Test
    public void testSaveActorCommand() throws Exception {
        //given
        ActorCommand command = new ActorCommand();
        command.setId(3L);
        command.setMovieCommandId(2L);

        Optional<Movie> movieOptional = Optional.of(new Movie());

        Movie savedMovie = new Movie();
        savedMovie.addActor(new Actor());
        savedMovie.getActors().iterator().next().setId(3L);

        when(movieRepository.findById(anyLong())).thenReturn(movieOptional);
        when(movieRepository.save(any())).thenReturn(savedMovie);

        //when
        ActorCommand savedCommand = actorService.saveOrUpdateActorCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(movieRepository, times(1)).findById(anyLong());
        verify(movieRepository, times(1)).save(any(Movie.class));

    }
}