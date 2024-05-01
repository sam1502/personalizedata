package com.nielsen.personalizeddata.repository;

import com.nielsen.personalizeddata.entity.ShopperPersonalizeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperPersonalizedProductRepository extends JpaRepository<ShopperPersonalizeProduct, Long> {
    @Query(value = "SELECT p.product_id, p.brand, ssp.relevancy_score, p.category, ssp.shopper_id " +
            "FROM product p " +
            "JOIN shopper_personalized_product ssp ON p.product_id = ssp.product_id " +
            "WHERE ssp.shopper_id=(:shopperId) " +
            "AND (:category IS NULL OR p.category = cast(:category AS text)) " +
            "AND (:brand IS NULL OR p.brand = cast(:brand AS text)) ", nativeQuery = true)
    Page<Object[]> findByShopperIdAndFilters(@Param("shopperId") String shopperId,
                                             @Param("brand") String brand,
                                             @Param("category") String category,
                                             Pageable pageable);

}
