package com.example.moviecatalog.controllers;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.services.ActorService;
import com.example.moviecatalog.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        model.addAttribute("movie", movieService.getMovieCommandById(Long.valueOf(movieId)));
        model.addAttribute("actor", actorService.findActorByMovieIdAndActorId((Long.valueOf(movieId)), Long.valueOf(actorId)));
        return "actor/show";
    }

    @GetMapping("movie/{movieId}/actor/new")
    public String newActor(@PathVariable String movieId, Model model){

        MovieCommand movieCommand = movieService.getMovieCommandById(Long.valueOf(movieId));

        ActorCommand actorCommand = new ActorCommand();
        actorCommand.setMovieCommandId(Long.valueOf(movieId));

        model.addAttribute("actor", actorCommand);
        return "actor/actorform";
    }

    @GetMapping("movie/{movieId}/actor/{actorId}/update")
    public String updateActor (@PathVariable String movieId, @PathVariable String actorId, Model model){
        model.addAttribute("actor", actorService.findActorByMovieIdAndActorId(Long.valueOf(movieId), Long.valueOf(actorId)));
        return "actor/actorform";
    }

    @GetMapping("movie/{movieId}/actor/{actorId}/delete")
    public String deleteActor(@PathVariable String movieId, @PathVariable String actorId){
        actorService.deleteById(Long.valueOf(movieId), Long.valueOf(actorId));

        return "redirect:/movie/" + movieId + "/actor";
    }


    @PostMapping("movie/{movieId}/actor")
    public String saveOrUpdateActor(@PathVariable String movieId, @ModelAttribute ActorCommand command){

        command.setMovieCommandId(Long.valueOf(movieId));

        ActorCommand savedActorCommand = actorService.saveOrUpdateActorCommand(command);

        return "redirect:/movie/"  + savedActorCommand.getMovieCommandId() + "/actor/" + savedActorCommand.getId() + "/show";
    }


}
