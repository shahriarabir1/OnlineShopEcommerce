package com.RaqamiUniverse.RaqamiOnlineShop.request;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Address;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
}
