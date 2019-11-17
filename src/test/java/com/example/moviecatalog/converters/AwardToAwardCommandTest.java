package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.domain.Award;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AwardToAwardCommandTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "Description";
    private AwardToAwardCommand converter;

    @BeforeEach
    void setUp() {
        converter = new AwardToAwardCommand();
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new Award()));
    }

    @Test
    void convert() {
        //given
        Award award = new Award();
        award.setId(LONG_VALUE);
        award.setDescription(DESCRIPTION);

        //when
        AwardCommand command = converter.convert(award);

        //then
        assertNotNull(award);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}