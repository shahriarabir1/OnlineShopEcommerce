package com.RaqamiUniverse.RaqamiOnlineShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private LocalDateTime offerStart;
    private LocalDateTime offerEnd;

    @OneToMany(mappedBy = "offer",cascade = CascadeType.ALL)
    private List<Product> product;
}
