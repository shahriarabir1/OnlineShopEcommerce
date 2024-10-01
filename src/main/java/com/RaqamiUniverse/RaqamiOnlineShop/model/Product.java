package com.RaqamiUniverse.RaqamiOnlineShop.model;

import com.RaqamiUniverse.RaqamiOnlineShop.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private Long inventory;
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Images>image;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @OneToMany(mappedBy ="product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Rating>ratings=new HashSet<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "product_shop",joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id")
    ,inverseJoinColumns = @JoinColumn(name = "shop_id",referencedColumnName = "id"))
    private Set<Shop>shop=new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "product_seller",joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "seller_id",referencedColumnName = "id"))
    private Set<Seller>seller=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;
    @ManyToOne
    @JoinColumn(name = "featured_product_id")
    private FeaturedProduct featuredProduct;

    private LocalDateTime created;

    public Product(String name, String description,String brand, Long inventory, Double discount, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.inventory = inventory;
        this.discount = discount;
        this.category = category;

    }

    public Double getAverageRating() {
        return ratings.stream()
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);
    }

}

