package com.example.moviecatalog.repositories;

import com.example.moviecatalog.domain.Actor;
import org.springframework.data.repository.CrudRepository;


public interface ActorRepository extends CrudRepository<Actor, Long> {

}
