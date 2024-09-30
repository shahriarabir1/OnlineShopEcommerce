package com.RaqamiUniverse.RaqamiOnlineShop.request;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private int inventory;
    private Double discount;
    private Category category;
}
