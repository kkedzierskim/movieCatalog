package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.domain.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorCommandToActorTest {


    private static final Long ACTOR_ID = 1L;
    private static final Long MOVIE_ID = 1L;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Wick";
    private static final String FILM_NAME = "John Wick";
    ActorCommandToActor converter;

    @BeforeEach
    void setUp() {
        converter = new ActorCommandToActor();
    }

    @Test
    void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new ActorCommand()));
    }

    @Test
    void convert() {
        //given
        ActorCommand command = new ActorCommand();
        command.setId(ACTOR_ID);
        command.setFirstName(FIRST_NAME);
        command.setLastName(LAST_NAME);
        command.setFilmName(FILM_NAME);
        command.setMovieCommandId(MOVIE_ID);

        //when
        Actor actor = converter.convert(command);

        //then
        assertNotNull(actor);
        assertEquals(ACTOR_ID, actor.getId());
        assertEquals(FIRST_NAME, actor.getFirstName());
        assertEquals(LAST_NAME, actor.getLastName());
        assertEquals(FILM_NAME, actor.getFilmName());
        assertEquals(MOVIE_ID, actor.getMovie().getId());
    }
}


