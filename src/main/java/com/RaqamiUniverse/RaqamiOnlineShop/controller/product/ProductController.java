package com.RaqamiUniverse.RaqamiOnlineShop.controller.product;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ProductDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.ProductNotFoundException;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Product;
import com.RaqamiUniverse.RaqamiOnlineShop.request.CreateProductRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.request.UpdateProductRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.response.ApiResponse;
import com.RaqamiUniverse.RaqamiOnlineShop.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/product/create")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody CreateProductRequest productRequest) {
        try {
            Product product=productService.addProduct(productRequest);
            ProductDto prod=productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Product Added Successfully", prod));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @GetMapping("/product/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> product=productService.getAllProducts();
            List<ProductDto> newProd=productService.getConvertedProducts(product);

            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }
    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProducts(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);

            return ResponseEntity.ok(new ApiResponse("Product deleted", null));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }
    @PutMapping("/product/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest productRequest,@RequestParam Long productId) {
        try {
            Product product=productService.updateProduct(productRequest,productId);
            return ResponseEntity.ok(new ApiResponse("Product deleted", product));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/category")
    public ResponseEntity<ApiResponse> getProductCategory(@RequestParam String category) {
        try {
            List<Product>products=productService.getProductsByCategory(category);
            List<ProductDto> newProd=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {
        try {
            List<Product>products=productService.getProductsByBrand(brand);
            List<ProductDto> newProd=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/category/brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String category,@RequestParam String brand) {
        try {
            List<Product>products=productService.getProductsByCategoryAndBrand(category,brand);
            List<ProductDto> newProd=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/inventory/{productId}")
    public ResponseEntity<ApiResponse> getInventory(@PathVariable Long productId) {
        try {
            Long inventory=productService.getInventory(productId);

            return ResponseEntity.ok(new ApiResponse("Products Found", inventory));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/offer")
    public ResponseEntity<ApiResponse> getProductsByOffer(@RequestParam Long offer) {
        try {
            List<Product>products=productService.getProductsByOffer(offer);
            List<ProductDto> newProd=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/discount")
    public ResponseEntity<ApiResponse> getProductByDiscount(@RequestParam Double discount) {
        try {
            List<Product>products=productService.getProductsByDiscount(discount);
            List<ProductDto> newProd=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/name/{name}")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name) {
        try {
            List<Product>products=productService.getProductsByName(name);
            List<ProductDto> newProd=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/name/brand")
    public ResponseEntity<ApiResponse> getProductsByNameAndBrand(@RequestParam String name,@RequestParam String brand) {
        try {
            List<Product>products=productService.getProductsByBrandAndName(name,brand);
            List<ProductDto> newProd=productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addAllProduct(@RequestBody List<CreateProductRequest> products) {
        try {
            List<Product>product=productService.addAllProducts(products);
            List<ProductDto> newProd=productService.getConvertedProducts(product);
            return ResponseEntity.ok(new ApiResponse("Products Found", newProd));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/brand/name/count")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand,@RequestParam String name) {
        try {
            Long count=productService.countProductsByBrandAndName(brand,name);
            return ResponseEntity.ok(new ApiResponse("Products Found", count));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/category/count")
    public ResponseEntity<ApiResponse> countProductsByCategory(@RequestParam String category) {
        try {
            Long count=productService.countProductsByCategory(category);
            return ResponseEntity.ok(new ApiResponse("Products Found", count));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/offer/count")
    public ResponseEntity<ApiResponse> countProductsByOffer(@RequestParam Long offer) {
        try {
            Long count=productService.countProductsByOffer(offer);
            return ResponseEntity.ok(new ApiResponse("Products Found", count));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/discount/count")
    public ResponseEntity<ApiResponse> countProductsByDiscount(@RequestParam Double discount) {
        try {
            Long count=productService.countProductsByDiscount(discount);
            return ResponseEntity.ok(new ApiResponse("Products Found", count));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/name/count")
    public ResponseEntity<ApiResponse> countProductsByName(@RequestParam String name) {
        try {
            Long count=productService.countProductsByName(name);
            return ResponseEntity.ok(new ApiResponse("Products Found", count));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/brand/count")
    public ResponseEntity<ApiResponse> countProductsByBrand(@RequestParam String brand) {
        try {
            Long count=productService.countProductsByBrand(brand);
            return ResponseEntity.ok(new ApiResponse("Products Found", count));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/product/category/brand/count")
    public ResponseEntity<ApiResponse> countProductsByCategoryAndBrand(@RequestParam String category,@RequestParam String brand) {
        try {
            Long count=productService.countProductsByCategoryAndBrand(category,brand);
            return ResponseEntity.ok(new ApiResponse("Products Found", count));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
