package com.RaqamiUniverse.RaqamiOnlineShop.repository;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndBrand(String productName, String productBrand);

    Product findByNameAndBrand(String name, String brand);

    List<Product> findByCategoryName(String category);

    List<Product> findByBrandName(String brand);
}
