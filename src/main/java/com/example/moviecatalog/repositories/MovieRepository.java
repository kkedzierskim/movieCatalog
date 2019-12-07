package com.example.moviecatalog.repositories;

import com.example.moviecatalog.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface MovieRepository extends CrudRepository<Movie, Long> {

    Set<Movie> findAllByTitleIgnoreCase(String Title);

}
