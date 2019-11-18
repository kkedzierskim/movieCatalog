package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovieCommandToMovie implements Converter<MovieCommand, Movie> {

    private final ActorCommandToActor actorConverter;
    private final AwardCommandToAward awardConverter;

    public MovieCommandToMovie(ActorCommandToActor actorConverter, AwardCommandToAward awardConverter) {
        this.actorConverter = actorConverter;
        this.awardConverter = awardConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Movie convert(MovieCommand source) {
        if (source == null){
            return null;
        }

        final Movie movie = new Movie();
        movie.setId(source.getId());
        movie.setBoxOffice(source.getBoxOffice());
        movie.setDescription(source.getDescription());
        movie.setGenre(source.getGenre());
        movie.setImage(source.getImage());
        movie.setProduction(source.getProduction());
        movie.setReleaseDate(source.getReleaseDate());
        movie.setTitle(source.getTitle());

        if (source.getActors() != null && source.getActors().size() > 0){
            source.getActors()
                    .forEach(actor -> movie.getActors().add(actorConverter.convert(actor)));
        }
        if (source.getAwards() != null && source.getAwards().size() > 0){
            source.getAwards()
                    .forEach(award -> movie.getAwards().add(awardConverter.convert(award)));
        }
        return movie;

    }
}
