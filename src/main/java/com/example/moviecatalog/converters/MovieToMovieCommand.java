package com.example.moviecatalog.converters;


import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class MovieToMovieCommand implements Converter<Movie, MovieCommand> {

    private final ActorToActorCommand actorConverter;
    private final AwardToAwardCommand awardConverter;


    public MovieToMovieCommand(ActorToActorCommand actorConverter, AwardToAwardCommand awardConverter) {
        this.actorConverter = actorConverter;
        this.awardConverter = awardConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public MovieCommand convert(Movie source) {
        if (source == null){
            return null;
        }

        final MovieCommand movieCommand = new MovieCommand();
        movieCommand.setId(source.getId());
        movieCommand.setBoxOffice(source.getBoxOffice());
        movieCommand.setDescription(source.getDescription());
        movieCommand.setGenre(source.getGenre());
        movieCommand.setImage(source.getImage());
        movieCommand.setProduction(source.getProduction());
        movieCommand.setReleaseDate(source.getReleaseDate());
        movieCommand.setTitle(source.getTitle());

        if (source.getActors() != null && source.getActors().size() > 0){
            source.getActors()
                    .forEach(actor -> movieCommand.getActors().add(actorConverter.convert(actor)));
        }
        if (source.getAwards() != null && source.getAwards().size() > 0){
            source.getAwards()
                    .forEach(award -> movieCommand.getAwards().add(awardConverter.convert(award)));
        }
        return movieCommand;
    }
}
