package com.RaqamiUniverse.RaqamiOnlineShop.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String phone;
}
