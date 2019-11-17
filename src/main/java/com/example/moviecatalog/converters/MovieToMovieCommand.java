package com.example.moviecatalog.converters;


import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class MovieToMovieCommand implements Converter<Movie, MovieCommand> {

    @Synchronized
    @Nullable
    @Override
    public MovieCommand convert(Movie source) {
        if (source == null){
            return null;
        }

        final MovieCommand movieCommand = new MovieCommand();
        movieCommand.setId(source.getId());
        movieCommand.setActors(source.getActors());
        movieCommand.setAwards(source.getAwards());
        movieCommand.setBoxOffice(source.getBoxOffice());
        movieCommand.setDescription(source.getDescription());
        movieCommand.setGenre(source.getGenre());
        movieCommand.setImage(source.getImage());
        movieCommand.setProduction(source.getProduction());
        movieCommand.setReleaseDate(source.getReleaseDate());
        movieCommand.setTittle(source.getTittle());
        return movieCommand;

    }
}
