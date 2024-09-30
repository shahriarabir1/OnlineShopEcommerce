package com.RaqamiUniverse.RaqamiOnlineShop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Shop {
    @Id
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;

    @ManyToMany(mappedBy = "shop")
    private List<Product>products;

    @OneToMany(mappedBy = "shop")
    private List<Rating> rating;
}
