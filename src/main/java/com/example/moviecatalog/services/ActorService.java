package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.domain.Actor;

import java.util.Set;

public interface ActorService {

    ActorCommand findActorByMovieIdAndActorId(Long movieId, Long actorId);

    ActorCommand saveOrUpdateActorCommand(ActorCommand command);

    void deleteById(Long movieId, Long idToDelete);
}

