package com.example.moviecatalog.controllers;


import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.services.AwardService;
import com.example.moviecatalog.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AwardController {

    private final AwardService awardService;
    private final MovieService movieService;

    public AwardController(AwardService awardService, MovieService movieService) {
        this.awardService = awardService;
        this.movieService = movieService;
    }


    @GetMapping("movie/{movieId}/award")
    public String getActors(@PathVariable String movieId, Model model) {

        model.addAttribute("movie", movieService.getMovieCommandById(Long.valueOf(movieId)));
        return "award/list";
    }

    @GetMapping("movie/{movieId}/award/{awardId}/show")
    public String getActor(@PathVariable String movieId, @PathVariable String awardId, Model model) {

        model.addAttribute("movie", movieService.getMovieCommandById(Long.valueOf(movieId)));
        model.addAttribute("award", awardService.findAwardByMovieIdAndAwardId((Long.valueOf(movieId)), Long.valueOf(awardId)));
        return "award/show";
    }

    @GetMapping("movie/{movieId}/award/{awardId}/update")
    public String updateActor (@PathVariable String movieId, @PathVariable String awardId, Model model){
        model.addAttribute("award", awardService.findAwardByMovieIdAndAwardId(Long.valueOf(movieId), Long.valueOf(awardId)));
        return "award/awardform";
    }

    @GetMapping("movie/{movieId}/award/new")
    public String newActor(@PathVariable String movieId, Model model){

        MovieCommand movieCommand = movieService.getMovieCommandById(Long.valueOf(movieId));

        AwardCommand awardCommand = new AwardCommand();
        awardCommand.setMovieCommandId(Long.valueOf(movieId));

        model.addAttribute("award", awardCommand);
        return "award/awardform";
    }

    @GetMapping("movie/{movieId}/award/{awardId}/delete")
    public String deleteActor(@PathVariable String movieId, @PathVariable String awardId){
        awardService.deleteById(Long.valueOf(movieId), Long.valueOf(awardId));

        return "redirect:/movie/" + movieId + "/award";
    }


    @PostMapping("movie/{movieId}/award")
    public String saveOrUpdateActor(@PathVariable String movieId, @ModelAttribute AwardCommand command){

        command.setMovieCommandId(Long.valueOf(movieId));

        AwardCommand savedAwardCommand = awardService.saveOrUpdateActorCommand(command);

        return "redirect:/movie/"  + savedAwardCommand.getMovieCommandId() + "/award/" + savedAwardCommand.getId() + "/show";
    }

}
