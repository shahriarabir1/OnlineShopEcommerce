package com.RaqamiUniverse.RaqamiOnlineShop.dto;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private String inventory;
    private Double discount;
    private Category category;
    private List<ImageDto> image;
}