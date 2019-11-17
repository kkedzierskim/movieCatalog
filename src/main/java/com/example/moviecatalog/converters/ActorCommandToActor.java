package com.example.moviecatalog.converters;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.domain.Actor;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActorCommandToActor implements Converter<ActorCommand, Actor> {


    @Synchronized
    @Nullable
    @Override
    public Actor convert(ActorCommand source) {
        if(source == null){
            return null;
        }

        final Actor actor = new Actor();
        actor.setId(source.getId());
        actor.setFilmName(source.getFilmName());
        actor.setFirstName(source.getFirstName());
        actor.setLastName(source.getLastName());

        return actor;
    }
}
