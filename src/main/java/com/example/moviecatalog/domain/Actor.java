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

    public Actor(String firstName, String lastName, String filmName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmName = filmName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String filmName;


    @ManyToOne
    private Movie movie;

   /* public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }*/
}
