package com.RaqamiUniverse.RaqamiOnlineShop.repository;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Address;
import com.RaqamiUniverse.RaqamiOnlineShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
    List<Address> findByUserId(Long userId);
}
