package com.example.moviecatalog.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String production;
    private Integer boxOffice;
    private LocalDate releaseDate;

    public Movie(String description, String production, Integer boxOffice, LocalDate releaseDate, Genre genre) {
        this.description = description;
        this.production = production;
        this.boxOffice = boxOffice;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    @OneToMany(mappedBy = "movie")
    private Set<Award> awards = new HashSet<>();


    @OneToMany
    private Set<Actor> actors = new HashSet<>();


    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Lob
    private Byte[] image;

    public Movie addActor(Actor actor){
        actor.setMovie(this);
        this.actors.add(actor);
        return this;
    }


}