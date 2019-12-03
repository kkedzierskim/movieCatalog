package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.converters.AwardCommandToAward;
import com.example.moviecatalog.converters.AwardToAwardCommand;
import com.example.moviecatalog.domain.Award;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.AwardRepository;
import com.example.moviecatalog.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class AwardServiceImpl implements AwardService{

    private final MovieRepository movieRepository;
    private final AwardToAwardCommand awardToAwardCommand;
    private final AwardRepository awardRepository;
    private final AwardCommandToAward awardCommandToAward;

    public AwardServiceImpl(MovieRepository movieRepository, AwardToAwardCommand awardToAwardCommand, AwardRepository awardRepository, AwardCommandToAward awardCommandToAward) {
        this.movieRepository = movieRepository;
        this.awardToAwardCommand = awardToAwardCommand;
        this.awardRepository = awardRepository;
        this.awardCommandToAward = awardCommandToAward;
    }

    @Override
    public AwardCommand findAwardByMovieIdAndAwardId(Long movieId, Long awardId) {

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (!optionalMovie.isPresent()) {
            log.error("movie id not found");
        }
        Movie movie = optionalMovie.get();

        Optional<AwardCommand> awardCommand = movie.getAwards().stream()
                .filter(award -> award.getId().equals(awardId))
                .map(award -> awardToAwardCommand.convert(award))
                .findFirst();

        if (!awardCommand.isPresent()) {
            //todo impl error handling
            log.error("actor id not found");
        }
        return awardCommand.get();
    }

    @Override
    public AwardCommand saveOrUpdateActorCommand(AwardCommand command) {


        Optional<Movie> movieOptional = movieRepository.findById(command.getMovieCommandId());

        if (!movieOptional.isPresent()) {
            log.error("movie not found");
            return new AwardCommand();
        } else {
            Movie movie = movieOptional.get();

            Optional<Award> awardOptional = movie
                    .getAwards()
                    .stream()
                    .filter(award -> award.getId().equals(command.getId()))
                    .findFirst();

            if (awardOptional.isPresent()) {
                Award awardFound = awardOptional.get();
                awardFound.setDescription(command.getDescription());

            } else {
                Award newAward = awardCommandToAward.convert(command);
                newAward.setMovie(movie);
                movie.addAward(newAward);
            }

            Movie savedMovie = movieRepository.save(movie);

            Optional<Award> savedAwardOptional = savedMovie.getAwards().stream()
                    .filter(movieActor -> movieActor.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if (!savedAwardOptional.isPresent()) {
                //not totally safe... But best guess
                savedAwardOptional = savedMovie.getAwards().stream()
                        .filter(movieAward -> movieAward.getDescription().equals(command.getDescription()))
                        .findFirst();
            }

            //to do check for fail
            return awardToAwardCommand.convert(savedAwardOptional.get());
        }
    }

    @Transactional
    @Override
    public void deleteById(Long movieId, Long idToDelete) {

        log.debug("Deleting award: " + idToDelete);

        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            log.debug("found movie");

            Optional<Award> awardOptional = movie
                    .getAwards()
                    .stream()
                    .filter(award -> award.getId().equals(idToDelete))
                    .findFirst();

            if (awardOptional.isPresent()) {
                log.debug("found actor");
                Award awardToDelete = awardOptional.get();
                awardToDelete.setMovie(null);
                movie.getActors().remove(awardOptional.get());
                movieRepository.save(movie);
                awardRepository.deleteById(idToDelete);
            }
        } else {
            log.debug("movie Id Not found. Id:" + movieId);
        }
    }
}
