package com.RaqamiUniverse.RaqamiOnlineShop.repository;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndBrand(String productName, String productBrand);

    Product findByNameAndBrand(String name, String brand);

    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);


    List<Product> findByOfferId(Long offerId);

    List<Product> findByDiscount(Double discount);

    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);
}
