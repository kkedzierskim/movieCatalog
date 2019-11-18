package com.example.moviecatalog.domain;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "actors")
public class Actor {

    public Actor(String firstName, String lastName, String filmName, Movie movie) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmName = filmName;
        this.movie = movie;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String filmName;


    @ManyToOne
    private Movie movie;



}
