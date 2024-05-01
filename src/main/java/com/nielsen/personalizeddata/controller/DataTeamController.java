package com.nielsen.personalizeddata.controller;

import com.nielsen.personalizeddata.dto.ProductMetaDataDTO;
import com.nielsen.personalizeddata.dto.ShopperPersonlizeProductDTO;
import com.nielsen.personalizeddata.entity.Product;
import com.nielsen.personalizeddata.service.ShopperProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class DataTeamController {

    @Autowired
    ShopperProductService shopperProductService;

    @PostMapping(path = "/shoppers")
    public ResponseEntity<String> saveShopperProductList(
            @Valid @RequestBody ShopperPersonlizeProductDTO shopperPersonalizedProductDTO) {
        shopperProductService.saveShopperProductList(shopperPersonalizedProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Shopper Personalized Products Saved Successfully");
    }

    @PostMapping(path = "/product-metadata")
    public Product persistProductMetadata(@RequestBody ProductMetaDataDTO productMetaDataDTO) {
        return shopperProductService.persistProductMetaData(productMetaDataDTO);
    }

}
