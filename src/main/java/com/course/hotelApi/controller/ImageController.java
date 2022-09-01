package com.course.hotelApi.controller;

import com.course.hotelApi.entity.Image;
import com.course.hotelApi.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{hotelId}/image")
    List<Image> getAll(@PathVariable Integer hotelId) {
        return imageService.getAll(hotelId);
    }

    @PostMapping("/{hotelId}/image")
    public Image addImage(@PathVariable Integer hotelId, @RequestParam MultipartFile file,
                          @RequestBody Image image) throws IOException {
        return imageService.addImage(hotelId, file, image);
    }

    @DeleteMapping("/{hotelId}/image")
    public void delete(@PathVariable Integer hotelId) {
        imageService.delete(hotelId);
    }
}
