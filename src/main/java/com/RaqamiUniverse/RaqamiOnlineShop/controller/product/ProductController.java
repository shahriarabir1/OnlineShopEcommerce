package com.RaqamiUniverse.RaqamiOnlineShop.controller.product;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ProductDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.ProductNotFoundException;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Product;
import com.RaqamiUniverse.RaqamiOnlineShop.response.ApiResponse;
import com.RaqamiUniverse.RaqamiOnlineShop.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product=productService.getProduct(productId);
            ProductDto prod=productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Product Founded", prod));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
