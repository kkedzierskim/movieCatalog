package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCommandToMovieTest {

    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    private MovieCommandToMovie converter;

    @BeforeEach
    void setUp() {
        converter = new MovieCommandToMovie();
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new MovieCommand()));
    }

    @Test
    void convert() {
        //given
        MovieCommand command = new MovieCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        Movie movie = converter.convert(command);

        //then
        assertNotNull(movie);
        assertEquals(LONG_VALUE, movie.getId());
        assertEquals(DESCRIPTION, movie.getDescription());
    }
}