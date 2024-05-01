package com.nielsen.personalizeddata.repository;

import com.nielsen.personalizeddata.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, String> {
    Optional<Shopper> findById(String shopperId);
}
