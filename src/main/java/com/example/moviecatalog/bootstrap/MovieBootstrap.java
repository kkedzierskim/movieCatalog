package com.example.moviecatalog.bootstrap;

import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Award;
import com.example.moviecatalog.domain.Genre;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.ActorRepository;
import com.example.moviecatalog.repositories.AwardRepository;
import com.example.moviecatalog.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class MovieBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final MovieRepository movieRepository;
    private final AwardRepository awardRepository;
    private final ActorRepository actorRepository;

    public MovieBootstrap(MovieRepository movieRepository, AwardRepository awardRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.awardRepository = awardRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        movieRepository.saveAll(getMovies());
        log.debug("bootstrap data upload");
    }

    private List<Movie> getMovies(){

        List<Movie> movies = new ArrayList<>(4);

        Movie greenMile = Movie.builder()
                .title("Green Mile")
                .description("The lives of guards on Death Row are affected by one of their charges: " +
                        "a black man accused of child murder and rape, yet who has a mysterious gift.")
                .genre(Genre.DRAMA)
                .boxOffice(1111111)
                .production("USA")
                .releaseDate(LocalDate.of(2000, 12, 6))
                .build();


        Movie joker = Movie.builder()
                .title("Joker")
                .description("In Gotham City, mentally-troubled comedian Arthur Fleck is disregarded and mistreated" +
                        " by society. He then embarks on a downward spiral of revolution and bloody crime. This " +
                        "path brings him face-to-face with his alter-ego")
                .genre(Genre.DRAMA)
                .boxOffice(22222222)
                .production("USA")
                .releaseDate(LocalDate.of(2019, 10, 13))
                .build();


        Movie gladiator = Movie.builder()
                .title("Gladiator")
                .description("A former Roman General sets out to exact vengeance against the corrupt emperor" +
                        " who murdered his family and sent him into slavery.")
                .genre(Genre.DRAMA)
                .boxOffice(33333333)
                .production("USA")
                .releaseDate(LocalDate.of(2000, 01, 01))
                .build();



        Actor greenMileActor1 = new Actor("Tom", "Hanks", "Paul Edgecomb", greenMile);
        Actor greenMileActor2 = new Actor("David", "Morse", "Brutus Brtual Howell", greenMile);
        Actor greenMileActor3 = new Actor("Bonnie", "Hunt", "Jan Edgecomb", greenMile);

        Set<Actor> greenMileActors = new HashSet<>();
        greenMileActors.add(greenMileActor1);
        greenMileActors.add(greenMileActor2);
        greenMileActors.add(greenMileActor3);

        Actor jokerActor1 = new Actor("Joaquin", "Phoenix", "Joker", joker);
        Actor jokerActor2 = new Actor("Robert", "De Niro", "Murray Franklin", joker);
        Actor jokerActor3 = new Actor("Zazie", "Beetz", "Sophie Dumond", joker);

        Set<Actor> jokerActors = new HashSet<>();
        jokerActors.add(jokerActor1);
        jokerActors.add(jokerActor2);
        jokerActors.add(jokerActor3);

        Actor gladiatorActor1 = new Actor("Russell", "Crowe", "Maximus", gladiator);
        Actor gladiatorActor2 = new Actor("Joaquin", "Phoenix", "Kommodus Franklin", gladiator);
        Actor gladiatorActor3 = new Actor("Connie", "Nielsen", "Lucilla", gladiator);

        Set<Actor> gladiatorActors = new HashSet<>();
        gladiatorActors.add(gladiatorActor1);
        gladiatorActors.add(gladiatorActor2);
        gladiatorActors.add(gladiatorActor3);

        Award oscar = Award.builder()
                .description("Oscar Award - best Actor in 'Green Mile movie' ")
                .movie(greenMile)
                .build();
        Set<Award> greenMileAwards = new HashSet<>();
        greenMileAwards.add(oscar);


        greenMile.setActors(greenMileActors);
        joker.setActors(jokerActors);
        gladiator.setActors(gladiatorActors);

        movies.add(gladiator);
        movies.add(joker);
        movies.add(greenMile);

        awardRepository.save(oscar);

        actorRepository.saveAll(greenMileActors);
        actorRepository.saveAll(jokerActors);
        actorRepository.saveAll(gladiatorActors);

        return movies;

    }

}
