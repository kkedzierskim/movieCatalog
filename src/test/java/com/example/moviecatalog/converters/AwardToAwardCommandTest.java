package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.domain.Award;
import com.example.moviecatalog.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AwardToAwardCommandTest {

    private static final Long AWARD_ID = 1L;
    private static final Long MOVIE_COMMAND_ID = 1L;
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
        award.setId(AWARD_ID);
        award.setDescription(DESCRIPTION);
        Movie movie = new Movie();
        movie.setId(1L);
        award.setMovie(movie);


        //when
        AwardCommand command = converter.convert(award);

        //then
        assertNotNull(award);
        assertEquals(AWARD_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(MOVIE_COMMAND_ID, command.getMovieCommandId());
    }
}