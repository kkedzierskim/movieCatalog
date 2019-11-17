package com.example.moviecatalog.commands;


import com.example.moviecatalog.domain.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActorCommand {

    private Long id;
    private String firstName;
    private String lastName;
    private String filmName;
    private Long movieCommandId;


}
