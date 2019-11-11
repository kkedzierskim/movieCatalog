package com.example.moviecatalog.controllers;


import com.example.moviecatalog.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    private final MovieService movieService;

    public IndexController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping({"/", "index", ""})
    public String getIndexPage(Model model){
        model.addAttribute("movies", movieService.getMovies());
        return "index";
    }
}
