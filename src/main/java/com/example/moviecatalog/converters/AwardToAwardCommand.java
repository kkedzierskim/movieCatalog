package com.example.moviecatalog.converters;


import com.example.moviecatalog.commands.AwardCommand;
import com.example.moviecatalog.domain.Award;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AwardToAwardCommand implements Converter<Award, AwardCommand> {

    @Synchronized
    @Nullable
    @Override
    public AwardCommand convert(Award source) {
        if(source == null) {
            return null;
        }

        final AwardCommand awardCommand = new AwardCommand();
        awardCommand.setId(source.getId());
        awardCommand.setDescription(source.getDescription());

        if (source.getMovie() != null){
            awardCommand.setMovieCommandId(source.getMovie().getId());
        }

        return awardCommand;
    }
}
