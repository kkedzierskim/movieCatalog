package com.example.moviecatalog.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String production;
    private Integer boxOffice;
    private LocalDate releaseDate;


    @OneToMany(mappedBy = "movie")
    private Set<Award> awards = new HashSet<>();


    @ManyToMany
    private Set<Actor> actors = new HashSet<>();


    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Lob
    private Byte[] image;
}