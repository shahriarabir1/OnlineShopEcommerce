package com.RaqamiUniverse.RaqamiOnlineShop.dto;

import com.RaqamiUniverse.RaqamiOnlineShop.model.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Address> address;
    private Cart cart;
    private List<Order> orders;
    private List<Favorite>favorites;
    private List<Review>reviews;
    private List<DeliveryStatus>deliveryStatuses;
    private List<Blog>blogs;
    private List<News>news;
    private List<Rating>ratings;
}
