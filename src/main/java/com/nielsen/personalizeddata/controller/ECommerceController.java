package com.nielsen.personalizeddata.controller;

import com.nielsen.personalizeddata.dto.ShopperProductResponseDTO;
import com.nielsen.personalizeddata.service.EcommerceService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("/api/external")
public class ECommerceController {

    @Autowired
    EcommerceService ecommerceService;


    @GetMapping("/shoppers/{shopperId}/products")
    public ResponseEntity<Page<ShopperProductResponseDTO>> getShopperProducts(
            @PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "1") @Positive int page,
            @RequestParam(defaultValue = "10") @Positive @Max(value = 100) int size) {

        Page<ShopperProductResponseDTO> productsPage = ecommerceService
                .getShopperProducts(shopperId, category, brand, page, size);
        if (productsPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productsPage);
        }
    }
}
