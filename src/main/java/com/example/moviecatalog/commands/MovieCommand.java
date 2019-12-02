package com.example.moviecatalog.commands;

import com.example.moviecatalog.domain.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieCommand {
    private Long id;


    @NotBlank
    @Size(min = 1, max = 255)
    private String title;


    @NotBlank
    @Size(min = 20, max = 1000)
    private String description;

    @NotBlank
    @Size(min = 1, max = 255)
    private String production;


    @Min(1)
    private Integer boxOffice;
    private LocalDate releaseDate;
    private Genre genre;
    private Byte[] image;
    private Set<ActorCommand> actors = new HashSet<>();
    private Set<AwardCommand> awards = new HashSet<>();
}
