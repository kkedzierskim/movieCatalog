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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
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

        List<Movie> movies = new ArrayList<>(12);



        Movie silentOfTheLambs  = Movie.builder()
                .title("The Silence of the Lamb")
                .description("A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal" +
                        " killer to help catch another serial killer, a madman who skins his victims.")
                .genre(Genre.THRILLER)
                .boxOffice(272_753_884)
                .production("USA")
                .releaseDate(LocalDate.of(1991, 02, 18))
                .build();

        Movie indianaJones  = Movie.builder()
                .title("Indiana Jones and the Temple of Doom")
                .description("In 1935, Indiana Jones arrives in India, still part of the British Empire, and is asked " +
                        "to find a mystical stone. He then stumbles upon a secret cult committing enslavement and human" +
                        " sacrifices in the catacombs of an ancient palace.")
                .genre(Genre.ADVENTURE)
                .boxOffice(333_107_271)
                .production("USA")
                .releaseDate(LocalDate.of(1984, 05, 27))
                .build();



        Movie saw  = Movie.builder()
                .title("Saw")
                .description("TTwo strangers, who awaken in a room with no recollection of how they got there," +
                        " soon discover they're pawns in a deadly game perpetrated by a notorious serial killer.")
                .genre(Genre.HORROR)
                .boxOffice(103_911_669)
                .production("USA")
                .releaseDate(LocalDate.of(2004, 10, 31))
                .build();


        Movie hangover  = Movie.builder()
                .title("The hangover")
                .description("Three buddies wake up from a bachelor party in Las Vegas, with no memory of the " +
                        "previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding.")
                .genre(Genre.COMEDY)
                .boxOffice(467_517_116)
                .production("USA")
                .releaseDate(LocalDate.of(2009, 07, 07))
                .build();

        Movie intouchables  = Movie.builder()
                .title("Intouchables")
                .description("After he becomes a quadriplegic from a paragliding accident," +
                        " an aristocrat hires a young man from the projects to be his caregiver.")
                .genre(Genre.COMEDY)
                .boxOffice(426_588_510)
                .production("France")
                .releaseDate(LocalDate.of(2012, 05, 27))
                .build();



        Movie deadpool = Movie.builder()
                .title("Deadpool")
                .description("A wisecracking mercenary gets experimented on and becomes immortal but ugly, " +
                        "and sets out to track down the man who ruined his looks.")
                .genre(Genre.COMEDY)
                .boxOffice(782_612_155)
                .production("USA")
                .releaseDate(LocalDate.of(2016, 02, 12))
                .build();


        Movie avatar = Movie.builder()
                .title("Avatar")
                .description("A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between " +
                        "following his orders and protecting the world he feels is his home.")
                .genre(Genre.ACTION)
                .boxOffice(2_111_968_301)
                .production("USA")
                .releaseDate(LocalDate.of(2009, 12, 25))
                .build();


        Movie greenMile = Movie.builder()
                .title("Green Mile")
                .description("The lives of guards on Death Row are affected by one of their charges: " +
                        "a black man accused of child murder and rape, yet who has a mysterious gift.")
                .genre(Genre.DRAMA)
                .boxOffice(286_801_374)
                .production("USA")
                .releaseDate(LocalDate.of(2000, 3, 24))
                .build();


        Movie joker = Movie.builder()
                .title("Joker")
                .description("In Gotham City, mentally-troubled comedian Arthur Fleck is disregarded and mistreated" +
                        " by society. He then embarks on a downward spiral of revolution and bloody crime. This " +
                        "path brings him face-to-face with his alter-ego")
                .genre(Genre.DRAMA)
                .boxOffice(1_050_891_623)
                .production("USA")
                .releaseDate(LocalDate.of(2019, 10, 4))
                .build();


        Movie gladiator = Movie.builder()
                .title("Gladiator")
                .description("A former Roman General sets out to exact vengeance against the corrupt emperor" +
                        " who murdered his family and sent him into slavery.")
                .genre(Genre.DRAMA)
                .boxOffice(460_583_960)
                .production("USA")
                .releaseDate(LocalDate.of(2000, 6, 14))
                .build();





        Actor avatarActor1 = new Actor("Sam", "Worthington", "Jake Sully", avatar);
        Actor avatarActor2 = new Actor("Zoe", "Saldana", "Neytiri", avatar);
        Actor avatarActor3 = new Actor("Sigourney", "Weaver", "Dr. Grace Augistine", avatar);

        Set<Actor> avatarActors = new HashSet<>();
        avatarActors.add(avatarActor1);
        avatarActors.add(avatarActor2);
        avatarActors.add(avatarActor3);
        avatar.setActors(avatarActors);

        Actor greenMileActor1 = new Actor("Tom", "Hanks", "Paul Edgecomb", greenMile);
        Actor greenMileActor2 = new Actor("David", "Morse", "Brutus Brtual Howell", greenMile);
        Actor greenMileActor3 = new Actor("Bonnie", "Hunt", "Jan Edgecomb", greenMile);

        Set<Actor> greenMileActors = new HashSet<>();
        greenMileActors.add(greenMileActor1);
        greenMileActors.add(greenMileActor2);
        greenMileActors.add(greenMileActor3);
        greenMile.setActors(greenMileActors);

        Actor jokerActor1 = new Actor("Joaquin", "Phoenix", "Joker", joker);
        Actor jokerActor2 = new Actor("Robert", "De Niro", "Murray Franklin", joker);
        Actor jokerActor3 = new Actor("Zazie", "Beetz", "Sophie Dumond", joker);

        Set<Actor> jokerActors = new HashSet<>();
        jokerActors.add(jokerActor1);
        jokerActors.add(jokerActor2);
        jokerActors.add(jokerActor3);
        joker.setActors(jokerActors);

        Actor gladiatorActor1 = new Actor("Russell", "Crowe", "Maximus", gladiator);
        Actor gladiatorActor2 = new Actor("Joaquin", "Phoenix", "Kommodus Franklin", gladiator);
        Actor gladiatorActor3 = new Actor("Connie", "Nielsen", "Lucilla", gladiator);

        Set<Actor> gladiatorActors = new HashSet<>();
        gladiatorActors.add(gladiatorActor1);
        gladiatorActors.add(gladiatorActor2);
        gladiatorActors.add(gladiatorActor3);
        gladiator.setActors(gladiatorActors);

        Award oscar = Award.builder()
                .description("Oscar Award - best Actor in 'Green Mile movie'Oscar Award - best Actor in 'Green Mile movie'Oscar Award - best Actor in 'Green Mile movie'Oscar Award - best Actor in 'Green Mile movie' ")
                .movie(greenMile)
                .build();
        Set<Award> greenMileAwards = new HashSet<>();
        greenMileAwards.add(oscar);


        movies.add(gladiator);
        movies.add(joker);
        movies.add(greenMile);
        movies.add(avatar);
        movies.add(deadpool);
        movies.add(intouchables);
        movies.add(hangover);
        movies.add(saw);
        movies.add(indianaJones);
        movies.add(silentOfTheLambs);

        awardRepository.save(oscar);

        actorRepository.saveAll(greenMileActors);
        actorRepository.saveAll(jokerActors);
        actorRepository.saveAll(gladiatorActors);

        return movies;

    }

}
