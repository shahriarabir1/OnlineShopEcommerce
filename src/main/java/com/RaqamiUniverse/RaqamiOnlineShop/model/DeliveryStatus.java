package com.RaqamiUniverse.RaqamiOnlineShop.model;

import com.RaqamiUniverse.RaqamiOnlineShop.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus orderStatus;
    private BigDecimal deliveryPrice;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
