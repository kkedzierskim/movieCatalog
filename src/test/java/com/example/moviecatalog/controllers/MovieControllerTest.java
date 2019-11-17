package com.example.moviecatalog.controllers;

import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class MovieControllerTest {

    private MovieController movieController;

    @Mock
    MovieService movieService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        movieController = new MovieController(movieService);
    }

    @Test
    void showMovieById() throws Exception{

        //given
        Movie movie = new Movie();
        movie.setId(1L);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

        //when
        when(movieService.getMovieById(anyLong())).thenReturn(movie);

        //then
        mockMvc.perform(get("/movie/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("movie/show"));



    }
}