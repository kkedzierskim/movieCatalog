package com.example.moviecatalog.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Actor {

    public Actor(String firstName, String lastName, String filmName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmName = filmName;
    }

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




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
