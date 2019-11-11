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



        Actor greenMileActor1 = new Actor("Tom", "Hanks", "Paul Edgecomb");
        Actor greenMileActor2 = new Actor("David", "Morse", "Brutus Brtual Howell");
        Actor greenMileActor3 = new Actor("Bonnie", "Hunt", "Jan Edgecomb");

        Set<Actor> greenMileActors = new HashSet<>();
        greenMileActors.add(greenMileActor1);
        greenMileActors.add(greenMileActor2);
        greenMileActors.add(greenMileActor3);

        Actor jokerActor1 = new Actor("Joaquin", "Phoenix", "Joker");
        Actor jokerActor2 = new Actor("Robert", "De Niro", "Murray Franklin");
        Actor jokerActor3 = new Actor("Zazie", "Beetz", "Sophie Dumond");

        Set<Actor> jokerActors = new HashSet<>();
        jokerActors.add(jokerActor1);
        jokerActors.add(jokerActor2);
        jokerActors.add(jokerActor3);

        Actor gladiatorActor1 = new Actor("Russell", "Crowe", "Maximus");
        Actor gladiatorActor2 = new Actor("Joaquin", "Phoenix", "Kommodus Franklin");
        Actor gladiatorActor3 = new Actor("Connie", "Nielsen", "Lucilla");

        Award oscar = Award.builder()
                .description("Oscar Award - best Actor in 'Green Mile movie' ")
                .build();
        Set<Award> greenMileAwards = new HashSet<>();
        greenMileAwards.add(oscar);

        Set<Actor> gladiatorActors = new HashSet<>();
        gladiatorActors.add(gladiatorActor1);
        gladiatorActors.add(gladiatorActor2);
        gladiatorActors.add(gladiatorActor3);

        actorRepository.saveAll(greenMileActors);
        actorRepository.saveAll(jokerActors);
        actorRepository.saveAll(gladiatorActors);

        Movie greenMile = Movie.builder()
                .description("Green Mile")
                .actors(greenMileActors)
                .genre(Genre.DRAMA)
                .boxOffice(1111111)
                .production("USA")
                .releaseDate(LocalDate.of(2000, 12, 6))
                .awards(greenMileAwards)
                .build();
        movies.add(greenMile);

        Movie joker = Movie.builder()
                .description("Joker")
                .actors(jokerActors)
                .genre(Genre.DRAMA)
                .boxOffice(22222222)
                .production("USA")
                .releaseDate(LocalDate.of(2019, 10, 13))
                .build();
        movies.add(joker);

        Movie gladiator = Movie.builder()
                .description("Gladiator")
                .actors(gladiatorActors)
                .genre(Genre.DRAMA)
                .boxOffice(33333333)
                .production("USA")
                .releaseDate(LocalDate.of(2000, 01, 01))
                .build();
        movies.add(gladiator);

        return movies;

    }

}
