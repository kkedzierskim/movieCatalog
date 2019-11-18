package com.example.moviecatalog.controllers;


import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movie/{movieId}/show")
    public String showMovieById(@PathVariable String movieId, Model model){

        Movie movie = movieService.getMovieById(Long.valueOf(movieId));
        model.addAttribute("movie", movie);

        return "movie/show";
    }

    @GetMapping("movie/new")
    public String newMovie(Model model){
        model.addAttribute("movie", new MovieCommand());

        return "movie/movieform";
    }

    @PostMapping("movie")
    public String saveOrUpdate(@ModelAttribute MovieCommand command){
        MovieCommand savedMovieCommand = movieService.saveMovieCommand(command);

        return "redirect:/movie/show/" + savedMovieCommand.getId();
    }


}
