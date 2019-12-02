package com.example.moviecatalog.commands;


import com.example.moviecatalog.domain.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ActorCommand {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String lastName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String filmName;
    private Long movieCommandId;


}
