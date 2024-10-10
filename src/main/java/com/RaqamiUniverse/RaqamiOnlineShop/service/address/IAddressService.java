package com.RaqamiUniverse.RaqamiOnlineShop.service.address;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Address;
import com.RaqamiUniverse.RaqamiOnlineShop.request.AddressRequest;

import java.util.List;

public interface IAddressService {
    List<Address> getAddressByUserId(Long userId);
    Address addAddress(AddressRequest address, Long userId);
    Address updateAddress(AddressRequest address,Long id, Long userId);
    void deleteAddress(Long UserId);
}
