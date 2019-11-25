package com.example.moviecatalog.commands;

import com.example.moviecatalog.services.ImageService;
import com.example.moviecatalog.services.MovieService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
public class ImageController {

    private final ImageService imageService;
    private final MovieService movieService;

    public ImageController(ImageService imageService, MovieService movieService) {
        this.imageService = imageService;
        this.movieService = movieService;
    }

    @GetMapping("movie/{movieId}/image")
    public String showImageUploadForm(@PathVariable String movieId, Model model){
        model.addAttribute("movie", movieService.getMovieCommandById(Long.valueOf(movieId)));
        return "movie/imageuploadform";
    }

    @PostMapping("movie/{movieId}/image")
    public String handleImagePost(@PathVariable String movieId, @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(Long.valueOf(movieId), file);

        return "redirect:/movie/" + movieId + "/show";
    }

    @GetMapping("movie/{movieId}/movieimage")
    public void renderImageFromDB(@PathVariable String movieId, HttpServletResponse response) throws IOException {
        MovieCommand movieCommand = movieService.getMovieCommandById(Long.valueOf(movieId));

        if (movieCommand.getImage() != null) {
            byte[] byteArray = new byte[movieCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : movieCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
