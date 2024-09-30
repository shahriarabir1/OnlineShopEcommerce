package com.RaqamiUniverse.RaqamiOnlineShop.service.product;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ProductDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.AlreadyExistsException;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.ProductNotFoundException;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Category;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Product;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.CategoryRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.ProductRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.request.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product addProduct(CreateProductRequest product) {
        if(productExist(product.getName(),product.getBrand())){
           Product prod=productRepository.findByNameAndBrand(product.getName(),product.getBrand());
           int inventory=prod.getInventory()+1;
           prod.setInventory(inventory);
           return productRepository.save(prod);
        }
        Category category= Optional.ofNullable(categoryRepository.findByName(product.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(product.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        product.setCategory(category);
        return productRepository.save(createProduct(product,category));
    }

    private Product createProduct(CreateProductRequest request,Category category){
        return new Product(request.getName(),
                request.getDescription(),
                request.getBrand(),
                request.getInventory(),
                request.getDiscount(),
                request.getPrice(),
                category);
    }
    private boolean productExist(String productName,String productBrand) {
       return productRepository.existsByNameAndBrand(productName,productBrand);

    }
    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByOffer(Long offerId) {
        return List.of();
    }

    @Override
    public Long getInventory(Long productId) {
        return 0L;
    }

    @Override
    public List<Product> getProductsByDiscount(Double discount) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return List.of();
    }

    @Override
    public List<Product> addAllProducts(List<Product> products) {
        return List.of();
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return 0L;
    }

    @Override
    public Long countProductsByCategory(String category) {
        return 0L;
    }

    @Override
    public Long countProductsByOffer(Long offerId) {
        return 0L;
    }

    @Override
    public Long countProductsByDiscount(Double discount) {
        return 0L;
    }

    @Override
    public Long countProductsByName(String name) {
        return 0L;
    }

    @Override
    public Long countProductsByBrand(String brand) {
        return 0L;
    }

    @Override
    public Long countProductsByCategoryAndBrand(String category, String brand) {
        return 0L;
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return List.of();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return null;
    }
}
