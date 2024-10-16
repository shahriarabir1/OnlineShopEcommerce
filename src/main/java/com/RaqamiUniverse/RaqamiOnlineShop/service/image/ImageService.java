package com.RaqamiUniverse.RaqamiOnlineShop.service.image;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ImageDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.ProductNotFoundException;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Images;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Product;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.ImageRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Images> getImagesByUserId(Long productId) {
        List<Images>images=imageRepository.findByProductId(productId);
        if(images.isEmpty()){
            throw new ProductNotFoundException("Images not found");
        }
        return images;
    }

    @Override
    public Images getImageById(Long imageId) {
      return imageRepository.findById(imageId).orElseThrow(()->new ProductNotFoundException("Images not found"));
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product not found"));
       List<ImageDto> imageDtoList=new ArrayList<>();
       for(MultipartFile file : files){
           try {
               Images image=new Images();
               image.setProduct(product);
               image.setImages(new SerialBlob(file.getBytes()));
               image.setFileName(file.getOriginalFilename());
               image.setFileType(file.getContentType());
               String buildDownloadUrl = "/api/v1/images/image/download/";
               String downloadUrl = buildDownloadUrl+image.getId();
               image.setDownloadUrl(downloadUrl);
               Images img=imageRepository.save(image);
               String newUrl=buildDownloadUrl+img.getId();
               img.setDownloadUrl(newUrl);
               imageRepository.save(img);
               ImageDto imageDto=new ImageDto();
               imageDto.setId(img.getId());
               imageDto.setDownloadUrl(newUrl);
               imageDto.setFileName(img.getFileName());
               imageDtoList.add(imageDto);
           } catch (SQLException | IOException e) {
               throw new RuntimeException(e);
           }
       }
       return imageDtoList;
    }

    @Override
    public Images updateImage(Images image) {
        return null;
    }

    @Override
    public Images deleteImage(Long imageId) {
        return null;
    }


}
