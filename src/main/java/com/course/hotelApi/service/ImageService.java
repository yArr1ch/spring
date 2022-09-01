package com.course.hotelApi.service;

import com.course.hotelApi.entity.Image;
import com.course.hotelApi.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> getAll(Integer hotelId) {
        return imageRepository.findAll().stream().filter(i -> i.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

    public Image addImage(Integer hotelId, MultipartFile file, Image image) throws IOException {
        image.setHotelId(hotelId);
        image.setImage(file.getBytes());
        return imageRepository.save(image);
    }

    public void delete(Integer hotelId) {
        imageRepository.deleteAll(getAll(hotelId));
    }
}
