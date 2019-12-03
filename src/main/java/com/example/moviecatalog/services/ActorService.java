package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.ActorCommand;

public interface ActorService {

    ActorCommand findActorByMovieIdAndActorId(Long movieId, Long actorId);

    ActorCommand saveOrUpdateActorCommand(ActorCommand command);

    void deleteById(Long movieId, Long idToDelete);
}

