package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorToActorCommandTest {

    private static final Long ACTOR_ID = 1L;
    private static final Long MOVIE_ID = 1L;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Wick";
    private static final String FILM_NAME = "John Wick";
    private ActorToActorCommand converter;

    @BeforeEach
    void setUp() {
        converter = new ActorToActorCommand();
    }

    @Test
    void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Actor()));
    }

    @Test
    void convert() {
        //given
        Actor actor = new Actor();
        actor.setId(ACTOR_ID);
        actor.setFirstName(FIRST_NAME);
        actor.setLastName(LAST_NAME);
        actor.setFilmName(FILM_NAME);
        Movie movie = new Movie();
        movie.setId(1L);
        actor.setMovie(movie);

        //when
        ActorCommand actorCommand = converter.convert(actor);

        //then
        assertNotNull(actorCommand);
        assertEquals(ACTOR_ID, actorCommand.getId());
        assertEquals(FIRST_NAME, actorCommand.getFirstName());
        assertEquals(LAST_NAME, actorCommand.getLastName());
        assertEquals(FILM_NAME, actorCommand.getFilmName());
        assertEquals(MOVIE_ID, actorCommand.getMovieCommandId());
    }
}