package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Award;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AwardCommandToAwardTest {


    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "Description";
    AwardCommandToAward converter;

    @BeforeEach
    void setUp() {
        converter = new AwardCommandToAward();
    }

    @Test
    void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new AwardCommand()));
    }

    @Test
    void convert() {
        //given
        AwardCommand command = new AwardCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        Award award = converter.convert(command);

        //then
        assertNotNull(award);
        assertEquals(LONG_VALUE, award.getId());
        assertEquals(DESCRIPTION, award.getDescription());
    }
}