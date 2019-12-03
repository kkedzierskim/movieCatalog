package com.example.moviecatalog.services;

import com.example.moviecatalog.commands.ActorCommand;
import com.example.moviecatalog.commands.AwardCommand;

public interface AwardService {

    AwardCommand findAwardByMovieIdAndAwardId(Long movieId, Long actorId);

    AwardCommand saveOrUpdateActorCommand(AwardCommand command);

    void deleteById(Long movieId, Long idToDelete);
}
