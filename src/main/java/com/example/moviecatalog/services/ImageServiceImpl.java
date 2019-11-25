package com.example.moviecatalog.services;


import com.example.moviecatalog.domain.Movie;
import com.example.moviecatalog.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final MovieRepository movieRepository;

    public ImageServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long movieId, MultipartFile file) {

        try{
            Movie movie = movieRepository.findById(movieId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            movie.setImage(byteObjects);

            movieRepository.save(movie);

        } catch (IOException e) {
            log.error("problem with file uploading");
            e.printStackTrace();
        }
    }
}
