package com.RaqamiUniverse.RaqamiOnlineShop.security.service;

import com.RaqamiUniverse.RaqamiOnlineShop.model.User;
import com.RaqamiUniverse.RaqamiOnlineShop.repository.UserRepository;
import com.RaqamiUniverse.RaqamiOnlineShop.security.user.UserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return UserInformation.buildUserDetails(user);
    }
}
