package com.RaqamiUniverse.RaqamiOnlineShop.service.product;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ProductDto;
import com.RaqamiUniverse.RaqamiOnlineShop.model.*;
import com.RaqamiUniverse.RaqamiOnlineShop.request.CreateProductRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(CreateProductRequest product);
    Product getProduct(Long productId);
    List<Product> getAllProducts();
    void deleteProduct(Long productId);
    Product updateProduct(UpdateProductRequest product,Long productId);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByOffer(Long offerId);
    Long getInventory(Long productId);
    List<Product>getProductsByDiscount(Double discount);
    List<Product>getProductsByName(String name);
    List<Product>getProductsByBrandAndName(String brand, String name);
    List<Product>addAllProducts(List<CreateProductRequest> products);
    Long countProductsByBrandAndName(String brand, String name);
    Long countProductsByCategory(String category);
    Long countProductsByOffer(Long offerId);
    Long countProductsByDiscount(Double discount);
    Long countProductsByName(String name);
    Long countProductsByBrand(String brand);
    Long countProductsByCategoryAndBrand(String category, String brand);
    List<ProductDto>getConvertedProducts(List<Product>products);
    ProductDto convertToDto(Product product);

}
