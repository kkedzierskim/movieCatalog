package com.example.moviecatalog.converters;


import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.domain.Award;
import com.example.moviecatalog.domain.Movie;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AwardCommandToAward implements Converter<AwardCommand, Award> {


    @Synchronized
    @Nullable
    @Override
    public Award convert(AwardCommand source) {
        if(source == null) {
            return null;
        }

        final Award award = new Award();
        award.setId(source.getId());
        award.setDescription(source.getDescription());

        if(source.getMovieCommandId() != null){
            Movie movie = new Movie();
            movie.setId(source.getMovieCommandId());
            award.setMovie(movie);
            movie.addAward(award);
        }

        return award;
    }

}
