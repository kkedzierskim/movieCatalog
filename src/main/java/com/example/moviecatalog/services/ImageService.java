package com.example.moviecatalog.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long movieId, MultipartFile file);
}
