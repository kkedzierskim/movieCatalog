package com.example.moviecatalog.converters;


import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.domain.Actor;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActorToActorCommand implements Converter<Actor, ActorCommand> {

    @Synchronized
    @Nullable
    @Override
    public ActorCommand convert(Actor source) {
        if(source == null){
            return null;
        }

        final ActorCommand actorCommand = new ActorCommand();
        actorCommand.setId(source.getId());
        actorCommand.setFilmName(source.getFilmName());
        actorCommand.setFirstName(source.getFirstName());
        actorCommand.setLastName(source.getLastName());

        if(source.getMovie() != null){
            actorCommand.setMovieCommandId(source.getMovie().getId());
        }

        return actorCommand;
    }
}
