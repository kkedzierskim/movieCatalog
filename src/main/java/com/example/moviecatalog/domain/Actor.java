package com.example.moviecatalog.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String filmName;

    public Actor(String firstName, String lastName, String filmName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmName = filmName;
    }

    @ManyToOne
    private Movie movie;

}
