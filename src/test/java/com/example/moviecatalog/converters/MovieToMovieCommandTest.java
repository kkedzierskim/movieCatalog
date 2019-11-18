package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Award;
import com.example.moviecatalog.domain.Genre;
import com.example.moviecatalog.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieToMovieCommandTest {

    private static final Long MOVIE_ID = 1L;
    private static final Long ACTOR_ID1 = 1L;
    private static final Long ACTOR_ID2 = 2L;
    private static final Long AWARD_ID1 = 1L;
    private static final Long AWARD_ID2 = 2L;
    private static final String TITLE = "John Wick";
    private static final String DESCRIPTION = "film about John Wick";
    private static final String PRODUCTION = "USA";
    private static final Integer BOX_OFFICE = 1111;
    private static final LocalDate RELEASE_DATE = LocalDate.of(2019, 8, 01);
    private static final Genre GENRE = Genre.ACTION;

    private MovieToMovieCommand movieConverter;

    @BeforeEach
    void setUp() {
        movieConverter = new MovieToMovieCommand(new ActorToActorCommand(), new AwardToAwardCommand());
    }

    @Test
    void testNullParameter() throws Exception{
        assertNull(movieConverter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(movieConverter.convert(new Movie()));
    }

    @Test
    void convert() {
        //given
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        movie.setDescription(DESCRIPTION);
        movie.setTitle(TITLE);
        movie.setReleaseDate(RELEASE_DATE);
        movie.setProduction(PRODUCTION);
        movie.setGenre(GENRE);
        movie.setBoxOffice(BOX_OFFICE);

        Actor actor1 = new Actor();
        actor1.setId(ACTOR_ID1);
        Actor actor2 = new Actor();
        actor2.setId(ACTOR_ID2);

        movie.getActors().add(actor1);
        movie.getActors().add(actor2);

        Award award1 = new Award();
        award1.setId(AWARD_ID1);
        Award award2 = new Award();
        award1.setId(AWARD_ID2);

        movie.getAwards().add(award1);
        movie.getAwards().add(award2);

        //when
        MovieCommand movieCommand = movieConverter.convert(movie);

        //then
        assertNotNull(movieCommand);
        assertEquals(MOVIE_ID, movieCommand.getId());
        assertEquals(DESCRIPTION, movieCommand.getDescription());
        assertEquals(TITLE, movieCommand.getTitle());
        assertEquals(RELEASE_DATE, movieCommand.getReleaseDate());
        assertEquals(PRODUCTION, movieCommand.getProduction());
        assertEquals(GENRE, movieCommand.getGenre());
        assertEquals(BOX_OFFICE, movieCommand.getBoxOffice());
        assertEquals(2, movieCommand.getActors().size());
        assertEquals(2, movieCommand.getAwards().size());

    }
}