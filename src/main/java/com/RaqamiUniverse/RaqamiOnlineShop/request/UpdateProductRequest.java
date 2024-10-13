package com.RaqamiUniverse.RaqamiOnlineShop.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private Long inventory;
    private Double discount;
    private String category;
}
