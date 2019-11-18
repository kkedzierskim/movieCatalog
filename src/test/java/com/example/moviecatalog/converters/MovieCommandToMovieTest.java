package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Genre;
import com.example.moviecatalog.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieCommandToMovieTest {

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

    private MovieCommandToMovie movieConverter;

    @BeforeEach
    void setUp() {
        movieConverter = new MovieCommandToMovie(new ActorCommandToActor(), new AwardCommandToAward());
    }

    @Test
    void testNullParameter() throws Exception{
        assertNull(movieConverter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(movieConverter.convert(new MovieCommand()));
    }

    @Test
    void convert() {
        //given
        MovieCommand command = new MovieCommand();
        command.setId(MOVIE_ID);
        command.setDescription(DESCRIPTION);
        command.setTitle(TITLE);
        command.setReleaseDate(RELEASE_DATE);
        command.setProduction(PRODUCTION);
        command.setGenre(GENRE);
        command.setBoxOffice(BOX_OFFICE);

        ActorCommand actor1 = new ActorCommand();
        actor1.setId(ACTOR_ID1);
        ActorCommand actor2 = new ActorCommand();
        actor2.setId(ACTOR_ID2);

        command.getActors().add(actor1);
        command.getActors().add(actor2);

        AwardCommand award1 = new AwardCommand();
        award1.setId(AWARD_ID1);
        AwardCommand award2 = new AwardCommand();
        award1.setId(AWARD_ID2);

        command.getAwards().add(award1);
        command.getAwards().add(award2);

        //when
        Movie movie = movieConverter.convert(command);

        //then
        assertNotNull(movie);
        assertEquals(MOVIE_ID, movie.getId());
        assertEquals(DESCRIPTION, movie.getDescription());
        assertEquals(TITLE, movie.getTitle());
        assertEquals(RELEASE_DATE, movie.getReleaseDate());
        assertEquals(PRODUCTION, movie.getProduction());
        assertEquals(GENRE, movie.getGenre());
        assertEquals(BOX_OFFICE, movie.getBoxOffice());
        assertEquals(2, movie.getActors().size());
        assertEquals(2, movie.getAwards().size());

    }
}


