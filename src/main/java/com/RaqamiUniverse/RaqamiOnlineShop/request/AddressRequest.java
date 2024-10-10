package com.RaqamiUniverse.RaqamiOnlineShop.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddressRequest {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
