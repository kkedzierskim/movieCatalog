package com.example.moviecatalog.controllers;


import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.exceptions.NotFoundException;
import com.example.moviecatalog.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
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



    @GetMapping("movie/{movieId}/update")
    public String updateMovie(@PathVariable String movieId, Model model){

        MovieCommand movieCommand = movieService.getMovieCommandById(Long.valueOf(movieId));
        model.addAttribute("movie", movieCommand);

        return "movie/movieform";
    }

    @PostMapping("movie")
    public String saveOrUpdate(@Valid @ModelAttribute MovieCommand command){
        MovieCommand savedMovieCommand = movieService.saveMovieCommand(command);

        return "redirect:/movie/" + savedMovieCommand.getId() + "/show";
    }

    @GetMapping("movie/{movieId}/delete")
    public String deleteMovie(@PathVariable String movieId){
        movieService.deleteMovieById(Long.valueOf(movieId));
        return "redirect:/index";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception exception){
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

}
