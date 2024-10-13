package com.RaqamiUniverse.RaqamiOnlineShop.controller.address;


import com.RaqamiUniverse.RaqamiOnlineShop.exception.NoAddressFound;
import com.RaqamiUniverse.RaqamiOnlineShop.exception.UserNotFound;
import com.RaqamiUniverse.RaqamiOnlineShop.model.Address;
import com.RaqamiUniverse.RaqamiOnlineShop.request.AddressRequest;
import com.RaqamiUniverse.RaqamiOnlineShop.response.ApiResponse;
import com.RaqamiUniverse.RaqamiOnlineShop.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/address/user/{userId}")
    public ResponseEntity<ApiResponse>getAllAddresses(@PathVariable Long userId) {
        try {
            List<Address> addresses=addressService.getAddressByUserId(userId);
            return ResponseEntity.ok(new ApiResponse("Address Found", addresses));
        } catch (UserNotFound | NoAddressFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/address/{addressId}")
    public ResponseEntity<ApiResponse>getAddress(@PathVariable Long addressId) {
        try {
            Address addresses=addressService.getAddressById(addressId);
            return ResponseEntity.ok(new ApiResponse("Address Found", addresses));
        } catch (NoAddressFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping("/address/create")
    public ResponseEntity<ApiResponse>createAddress(@RequestBody AddressRequest address, @RequestParam Long userId) {
        try {
            Address addresses=addressService.addAddress(address,userId);
            return ResponseEntity.ok(new ApiResponse("Address Created", addresses));
        } catch (NoAddressFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/address/update")
    public ResponseEntity<ApiResponse>updateAddress(@RequestBody AddressRequest address,@RequestParam Long id, @RequestParam Long userId) {
        try {
            Address addresses=addressService.updateAddress(address,id,userId);
            return ResponseEntity.ok(new ApiResponse("Address Updated", addresses));
        } catch (NoAddressFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping("/address/delete/{addressId}")
    public ResponseEntity<ApiResponse>deleteAddress(@PathVariable Long addressId) {
        try {
            addressService.deleteAddress(addressId);
            return ResponseEntity.ok(new ApiResponse("Address Deleted", null));
        } catch (NoAddressFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
