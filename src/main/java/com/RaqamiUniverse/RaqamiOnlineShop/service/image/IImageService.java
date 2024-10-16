package com.RaqamiUniverse.RaqamiOnlineShop.service.image;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ImageDto;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Images;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

public interface IImageService {
    List<Images> getImagesByUserId(Long productId);
    Images getImageById(Long imageId);


    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    Images updateImage(Images image);
    Images deleteImage(Long imageId);


}
