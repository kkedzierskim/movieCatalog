package com.example.moviecatalog.repositories;

import com.example.moviecatalog.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {


}
