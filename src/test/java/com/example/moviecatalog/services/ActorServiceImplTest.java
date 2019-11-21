package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.converters.ActorToActorCommand;
import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Movie;
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

    @Mock
    MovieRepository movieRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        actorToActorCommand = new ActorToActorCommand();
        actorService = new ActorServiceImpl(movieRepository, actorToActorCommand);
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
}