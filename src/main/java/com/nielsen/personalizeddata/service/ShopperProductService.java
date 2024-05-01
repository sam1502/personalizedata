package com.nielsen.personalizeddata.service;

import com.nielsen.personalizeddata.dto.ProductMetaDataDTO;
import com.nielsen.personalizeddata.dto.ProductShelfDTO;
import com.nielsen.personalizeddata.dto.ShopperPersonlizeProductDTO;
import com.nielsen.personalizeddata.entity.Product;
import com.nielsen.personalizeddata.entity.Shopper;
import com.nielsen.personalizeddata.entity.ShopperPersonalizeProduct;
import com.nielsen.personalizeddata.repository.ProductRepository;
import com.nielsen.personalizeddata.repository.ShopperPersonalizedProductRepository;
import com.nielsen.personalizeddata.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShopperProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    ShopperPersonalizedProductRepository shopperPersonalizedProductRepository;

    public void saveShopperProductList(ShopperPersonlizeProductDTO shopperPersonlizeProductDTO) {
        String shopperId = shopperPersonlizeProductDTO.getShopperId();
        List<ProductShelfDTO> shelf = shopperPersonlizeProductDTO.getShelf();

        // check if shooper already present
        Shopper shopper = shopperRepository.findById(shopperId)
                .orElseGet(() -> {
                    Shopper newShopper = new Shopper();
                    newShopper.setShopperId(shopperId);
                    return shopperRepository.save(newShopper);
                });

        for(ProductShelfDTO currProduct : shelf) {
            Product product = productRepository.findById(currProduct.getProductId())
                    .orElseGet(() -> {
                        Product newProduct = new Product();
                        newProduct.setProductId(currProduct.getProductId());
                        return productRepository.save(newProduct);
                    });

            ShopperPersonalizeProduct shopperProduct = new ShopperPersonalizeProduct();
            shopperProduct.setShopper(shopper);
            shopperProduct.setProduct(product);
            shopperProduct.setRelevancyScore(currProduct.getRelevancyScore());

            try {
                shopperPersonalizedProductRepository.save(shopperProduct);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Skipping existing entry for productId: " + currProduct.getProductId());
            }
        }
    }

    public Product persistProductMetaData(ProductMetaDataDTO productMetadataDTO) {
        Product product = new Product();
        String productId = productMetadataDTO.getProductId();
        String category = productMetadataDTO.getCategory();
        String brand = productMetadataDTO.getBrand();

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();

            if (Objects.equals(product.getCategory(), category) && Objects.equals(product.getBrand(), brand)) {
                throw new IllegalArgumentException("Product metadata already exists for productId: " + productId);
            } else {
                product.setCategory(category);
                product.setBrand(brand);
                productRepository.save(product);
            }
        } else {
            product.setProductId(productId);
            product.setCategory(category);
            product.setBrand(brand);
            productRepository.save(product);
        }
        return product;
    }
}
