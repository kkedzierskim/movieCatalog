package com.example.moviecatalog.controllers;

import com.example.moviecatalog.services.ActorService;
import com.example.moviecatalog.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ActorController {

    private final MovieService movieService;
    private final ActorService actorService;

    public ActorController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @GetMapping("movie/{movieId}/actor")
    public String getActors(@PathVariable String movieId, Model model) {

        model.addAttribute("movie", movieService.getMovieCommandById(Long.valueOf(movieId)));
        return "actor/list";
    }

    @GetMapping("movie/{movieId}/actor/{actorId}/show")
    public String getActor(@PathVariable String movieId, @PathVariable String actorId, Model model) {

        model.addAttribute("actor", actorService.findActorByMovieIdAndActorId((Long.valueOf(movieId)), Long.valueOf(actorId)));
        return "actor/show";
    }


}
