package com.budziak.springmenuapp.service;


import com.budziak.springmenuapp.domain.Image;
import com.budziak.springmenuapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${upload.path}")
    private String folderPath;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = folderPath + file.getOriginalFilename();

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build();

        imageRepository.save(image);
        file.transferTo(new File(filePath));

        return image;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {

        Optional<Image> fileData = imageRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();

        return Files.readAllBytes(new File(filePath).toPath());
    }
}
