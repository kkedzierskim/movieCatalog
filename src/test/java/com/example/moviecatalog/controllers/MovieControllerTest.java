package com.example.moviecatalog.controllers;

import com.example.moviecatalog.commands.MovieCommand;
import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.exceptions.NotFoundException;
import com.example.moviecatalog.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MovieControllerTest {

    private MovieController movieController;
    private MockMvc mockMvc;

    @Mock
    MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        movieController = new MovieController(movieService);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void showMovieById() throws Exception{

        //given
        Movie movie = new Movie();
        movie.setId(1L);

        //when
        when(movieService.getMovieById(anyLong())).thenReturn(movie);

        //then
        mockMvc.perform(get("/movie/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("movie/show"))
                .andExpect(model().attributeExists("movie"));
    }

    @Test
    void testGetNewMovieForm() throws Exception{
        mockMvc.perform(get("/movie/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("movie/movieform"))
                .andExpect(model().attributeExists("movie"));
    }

    @Test
    void testPostNewRecipeForm() throws Exception{
        //given
        MovieCommand movieCommand = new MovieCommand();
        movieCommand.setId(3L);

        //when
        when(movieService.saveMovieCommand(any())).thenReturn(movieCommand);

        //then
        mockMvc.perform(post("/movie")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("title", "some title"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/movie/3/show"));
    }

    @Test
    void testGetUpdatedView() throws Exception{
        //given
        MovieCommand movieCommand = new MovieCommand();
        movieCommand.setId(3L);

        //when
        when(movieService.getMovieCommandById(anyLong())).thenReturn(movieCommand);

        //then
        mockMvc.perform(get("/movie/3/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("movie/movieform"))
                .andExpect(model().attributeExists("movie"));
    }

    @Test
    void testDelete() throws Exception{
        mockMvc.perform(get("/movie/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        verify(movieService, times(1)).deleteMovieById(anyLong());

    }

    @Test
    void testGetMovieNotFound() throws Exception{
        //when
        when(movieService.getMovieById(anyLong())).thenThrow(NotFoundException.class);
        //then
        mockMvc.perform(get("/movie/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }
    @Test
    void testGetMovieNumberFormatException() throws Exception{
        //when
        when(movieService.getMovieById(anyLong())).thenThrow(NumberFormatException.class);
        //then
        mockMvc.perform(get("/movie/dupa/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}