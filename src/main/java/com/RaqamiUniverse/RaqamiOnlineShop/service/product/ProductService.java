package com.RaqamiUniverse.RaqamiOnlineShop.service.product;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ImageDto;
import com.RaqamiUniverse.RaqamiOnlineShop.dto.ProductDto;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.ProductNotFoundException;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Category;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Images;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Product;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.CategoryRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.ImageRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.ProductRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.request.CreateProductRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;
    @Override
    public Product addProduct(CreateProductRequest product) {
        if(productExist(product.getName(),product.getBrand())){
            Product prod=productRepository.findByNameAndBrand(product.getName(),product.getBrand());
            Long inventory=prod.getInventory()+1;
            prod.setInventory(inventory);
            return productRepository.save(prod);
        }
        Category category= Optional.ofNullable(categoryRepository.findByName(product.getCategory()))
                .orElseGet(()->{
                    Category newCategory = new Category(product.getCategory());
                    Product prods=productRepository.findByNameAndBrand(product.getName(),product.getBrand());
                    newCategory.setProducts(List.of(prods));
                    return categoryRepository.save(newCategory);
                });
        product.setCategory(category.getName());
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
    public Product updateProduct(UpdateProductRequest product,Long productId) {
        Product prod=productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
        prod.setName(product.getName());
        prod.setDescription(product.getDescription());
        prod.setBrand(product.getBrand());
        prod.setInventory(product.getInventory());
        prod.setDiscount(product.getDiscount());
        prod.setPrice(product.getPrice());
        return productRepository.save(prod);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return Optional.ofNullable(productRepository.findByCategoryName(category))
                .orElseThrow(()->new ProductNotFoundException("Category not found"));

    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return Optional.ofNullable(productRepository.findByBrand(brand))
                .orElseThrow(()->new ProductNotFoundException("Brand items not found"));
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return Optional.ofNullable(productRepository.findByCategoryNameAndBrand(category,brand))
                .orElseThrow(()->new ProductNotFoundException("Category not found"));
    }

    @Override
    public List<Product> getProductsByOffer(Long offerId) {
        return Optional.ofNullable(productRepository.findByOfferId(offerId))
                .orElseThrow(()->new ProductNotFoundException("Offer not found"));
    }

    @Override
    public Long getInventory(Long productId) {
        Product prod= productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
        return prod.getInventory();
    }

    @Override
    public List<Product> getProductsByDiscount(Double discount) {
        return Optional.ofNullable(productRepository.findByDiscount(discount))
                .orElseThrow(()->new ProductNotFoundException("Product have  not discount"));
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return Optional.ofNullable(productRepository.findByName(name))
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return Optional.ofNullable(productRepository.findByBrandAndName(brand,name))
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> addAllProducts(List<CreateProductRequest> products) {
       return products.stream().map(this::addProduct).collect(Collectors.toList());

    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        List<Product> prod=getProductsByBrandAndName(brand,name);
        return (long) prod.size();

    }

    @Override
    public Long countProductsByCategory(String category) {
        List<Product> prod=getProductsByCategory(category);
        return (long) prod.size();
    }

    @Override
    public Long countProductsByOffer(Long offerId) {
        List<Product>prod=getProductsByOffer(offerId);
        return (long) prod.size();
    }

    @Override
    public Long countProductsByDiscount(Double discount) {
        List<Product>prod=getProductsByDiscount(discount);
        return (long) prod.size();
    }

    @Override
    public Long countProductsByName(String name) {
        List<Product>prod=getProductsByName(name);
        return (long) prod.size();
    }

    @Override
    public Long countProductsByBrand(String brand) {
        List<Product>prod=getProductsByBrand(brand);
        return (long) prod.size();
    }

    @Override
    public Long countProductsByCategoryAndBrand(String category, String brand) {
        List<Product>prod=getProductsByCategoryAndBrand(category,brand);
        return (long) prod.size();
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto dto=modelMapper.map(product,ProductDto.class);
        List<Images> images=imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDto=images
                .stream()
                .map(image -> modelMapper.map(image,ImageDto.class))
                .toList();

        dto.setImage(imageDto);
        return dto;
    }
}
