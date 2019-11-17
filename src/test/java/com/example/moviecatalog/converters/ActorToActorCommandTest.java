package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.domain.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorToActorCommandTest {

    private static final Long LONG_VALUE = 1L;
    private static final String FIRST_NAME = "John";
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
        actor.setId(LONG_VALUE);
        actor.setFirstName(FIRST_NAME);

        //when
        ActorCommand actorCommand = converter.convert(actor);

        //then
        assertNotNull(actorCommand);
        assertEquals(LONG_VALUE, actorCommand.getId());
        assertEquals(FIRST_NAME, actorCommand.getFirstName());
    }
}