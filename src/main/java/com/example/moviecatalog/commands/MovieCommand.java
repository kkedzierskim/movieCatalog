package com.example.moviecatalog.commands;

import com.example.moviecatalog.domain.Actor;
import com.example.moviecatalog.domain.Award;
import com.example.moviecatalog.domain.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieCommand {
    private Long id;
    private String title;
    private String description;
    private String production;
    private Integer boxOffice;
    private LocalDate releaseDate;
    private Genre genre;
    private Byte[] image;
    private Set<ActorCommand> actors = new HashSet<>();
    private Set<AwardCommand> awards = new HashSet<>();
}
