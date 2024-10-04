package com.RaqamiUniverse.RaqamiOnlineShop.repository;

import com.RaqamiUniverse.RaqamiOnlineShop.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {
    List<Images> findByProductId(Long id);
}
