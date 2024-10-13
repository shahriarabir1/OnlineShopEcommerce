package com.RaqamiUniverse.RaqamiOnlineShop.service.address;

import com.RaqamiUniverse.RaqamiOnlineShop.exception.NoAddressFound;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.UserNotFound;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Address;
import com.RaqamiUniverse.RaqamiOnlineShop.model.User;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.AddressRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.UserRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.request.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(()->new NoAddressFound("No Address Found"));

    }

    @Override
    public List<Address> getAddressByUserId(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new UserNotFound("User Not Found"));
        return userRepository.findByUserId(user.getId()).stream()
                .findAny()
                .map(Collections::singletonList)
                .orElseThrow
                        (() -> new NoAddressFound("No Address Found"));
    }

    @Override
    public Address addAddress(AddressRequest address, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFound("User Not Found"));
        Address address1=new Address(address.getCity()
                ,address.getStreet(),address.getZip(),address.getState(),address.getCountry());
        address1.setUser(user);
       return addressRepository.save(address1);

    }

    @Override
    public Address updateAddress(AddressRequest address, Long id, Long userId) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFound("User Not Found"));
        Address address1=getAddressById(id);
        address1.setId(id);
        address1.setCity(address.getCity());
        address1.setStreet(address.getStreet());
        address1.setZip(address.getZip());
        address1.setState(address.getState());
        address1.setCountry(address.getCountry());
        address1.setUser(user);
        return address1;
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address=getAddressById(addressId);
        addressRepository.delete(address);
    }
}
