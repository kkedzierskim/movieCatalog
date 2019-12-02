package com.example.moviecatalog.commands;
import com.example.moviecatalog.controllers.ImageController;
import com.example.moviecatalog.services.ImageService;
import com.example.moviecatalog.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageControllerTest {


        @Mock
        ImageService imageService;

        @Mock
        MovieService movieService;

        ImageController controller;

        MockMvc mockMvc;

        @BeforeEach
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);

            controller = new ImageController(imageService, movieService);
            mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }

        @Test
        public void getImageForm() throws Exception {
            //given
            MovieCommand command = new MovieCommand();
            command.setId(1L);

            when(movieService.getMovieCommandById(anyLong())).thenReturn(command);

            //when
            mockMvc.perform(get("/movie/1/image"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("movie"));

            verify(movieService, times(1)).getMovieCommandById(anyLong());

        }

        @Test
        public void handleImagePost() throws Exception {
            MockMultipartFile multipartFile =
                    new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                            "MovieCatalog".getBytes());

            mockMvc.perform(multipart("/movie/1/image").file(multipartFile))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/movie/1/show"));

            verify(imageService, times(1)).saveImageFile(anyLong(), any());
        }


        @Test
        public void renderImageFromDB() throws Exception {

            //given
            MovieCommand command = new MovieCommand();
            command.setId(1L);

            String s = "fake image text";
            Byte[] bytesBoxed = new Byte[s.getBytes().length];

            int i = 0;

            for (byte primByte : s.getBytes()){
                bytesBoxed[i++] = primByte;
            }

            command.setImage(bytesBoxed);

            when(movieService.getMovieCommandById(anyLong())).thenReturn(command);

            //when
            MockHttpServletResponse response = mockMvc.perform(get("/movie/1/movieimage"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse();

            byte[] responseBytes = response.getContentAsByteArray();

            assertEquals(s.getBytes().length, responseBytes.length);
        }
}