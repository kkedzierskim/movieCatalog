package com.example.moviecatalog.controllers;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.services.ActorService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ActorControllerTest {

    MockMvc mockMvc;
    ActorController actorController;

    @Mock
    MovieService movieService;
    @Mock
    ActorService actorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        actorController = new ActorController(movieService, actorService);
        mockMvc = MockMvcBuilders.standaloneSetup(actorController).build();
    }

    @Test
    void getActorsTest() throws Exception{
        //given
        MovieCommand movie = new MovieCommand();
        movie.setId(1L);

        //when
        when(movieService.getMovieCommandById(anyLong())).thenReturn(movie);

        //then
        mockMvc.perform(get("/movie/1/actor"))
                .andExpect(status().isOk())
                .andExpect(view().name("actor/list"))
                .andExpect(model().attributeExists("movie"));

    }
    @Test
    void showActorsTest() throws Exception{

    }
}