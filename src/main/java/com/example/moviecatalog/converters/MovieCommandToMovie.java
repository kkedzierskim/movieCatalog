package com.example.moviecatalog.converters;


import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovieCommandToMovie implements Converter<MovieCommand, Movie> {


    @Synchronized
    @Nullable
    @Override
    public Movie convert(MovieCommand source) {
        if (source == null){
            return null;
        }

        final Movie movie = new Movie();
        movie.setId(source.getId());
        movie.setActors(source.getActors());
        movie.setAwards(source.getAwards());
        movie.setBoxOffice(source.getBoxOffice());
        movie.setDescription(source.getDescription());
        movie.setGenre(source.getGenre());
        movie.setImage(source.getImage());
        movie.setProduction(source.getProduction());
        movie.setReleaseDate(source.getReleaseDate());
        movie.setTittle(source.getTittle());
        return movie;

    }
}
