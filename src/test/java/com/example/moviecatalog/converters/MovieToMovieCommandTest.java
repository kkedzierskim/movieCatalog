package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieToMovieCommandTest {

    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    private MovieToMovieCommand converter;

    @BeforeEach
    void setUp() {
        converter = new MovieToMovieCommand();
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Movie()));
    }

    @Test
    void convert() {
        //given
        Movie movie = new Movie();
        movie.setId(LONG_VALUE);
        movie.setDescription(DESCRIPTION);

        //when
        MovieCommand command = converter.convert(movie);

        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}