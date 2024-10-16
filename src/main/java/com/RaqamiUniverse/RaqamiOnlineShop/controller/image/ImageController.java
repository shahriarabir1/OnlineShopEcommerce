package com.RaqamiUniverse.RaqamiOnlineShop.controller.image;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ImageDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.ProductNotFoundException;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Images;
import com.RaqamiUniverse.RaqamiOnlineShop.response.ApiResponse;
import com.RaqamiUniverse.RaqamiOnlineShop.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("image/save")
    public ResponseEntity<ApiResponse> saveImage(@RequestBody List<MultipartFile> files, @RequestParam Long productId) {
        try {
            List<ImageDto> images=imageService.saveImages(files,productId);
            return ResponseEntity.ok(new ApiResponse("Product Founded", images));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse( e.getMessage(),null));
        }
    }
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<ApiResponse> downloadImage(@PathVariable Long imageId) {
        try {
            Images images=imageService.getImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("Product Founded", images));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse( e.getMessage(),null));
        }
    }
}
