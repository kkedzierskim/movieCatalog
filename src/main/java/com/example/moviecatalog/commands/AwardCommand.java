package com.example.moviecatalog.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AwardCommand {

    private Long id;

    @NotEmpty
    @NotBlank
    @Size(min = 20, max = 1000)
    private String description;
    private Long movieCommandId;


}
