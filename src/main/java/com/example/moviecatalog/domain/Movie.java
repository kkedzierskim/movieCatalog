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
    private String title;
    private String description;
    private String production;
    private Integer boxOffice;
    private LocalDate releaseDate;


    @Builder.Default
    @OneToMany(mappedBy = "movie")
    private Set<Award> awards = new HashSet<>();


    @Builder.Default
    @OneToMany(mappedBy = "movie")
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


    public Movie addAward(Award award){
        award.setMovie(this);
        this.awards.add(award);
        return this;
    }


}