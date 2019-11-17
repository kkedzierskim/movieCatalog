package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.domain.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorCommandToActorTest {

    private static final Long LONG_VALUE = 1L;
    private static final String FIRST_NAME = "John";
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
        command.setId(LONG_VALUE);
        command.setFirstName(FIRST_NAME);

        //when
        Actor actor = converter.convert(command);

        //then
        assertNotNull(actor);
        assertEquals(LONG_VALUE, actor.getId());
        assertEquals(FIRST_NAME, actor.getFirstName());
    }
}