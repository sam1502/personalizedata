package com.nielsen.personalizeddata.service;

import com.nielsen.personalizeddata.dto.PersonalizedProductDTO;
import com.nielsen.personalizeddata.dto.ShopperProductResponseDTO;
import com.nielsen.personalizeddata.entity.Product;
import com.nielsen.personalizeddata.repository.ShopperPersonalizedProductRepository;
import com.nielsen.personalizeddata.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EcommerceService {

    @Autowired
    ShopperPersonalizedProductRepository personalizedProductRepository;

    @Autowired
    ShopperRepository shopperRepository;

    public Page<ShopperProductResponseDTO> getShopperProducts(
            String shopperId,
            String category,
            String brand,
            int page,
            int limit
    ) {

        Pageable pageable = PageRequest.of(page - 1, limit);
            Page<Object[]> resultsPage = personalizedProductRepository
                    .findByShopperIdAndFilters(shopperId, brand, category, pageable);

            List<ShopperProductResponseDTO> responseDTOs = new ArrayList<>();
        for(Object[] result : resultsPage.getContent()) {
            ShopperProductResponseDTO responseDTO = new ShopperProductResponseDTO();

            PersonalizedProductDTO p = new PersonalizedProductDTO();
            p.setProductId((String) result[0]);
            p.setBrand((String) result[1]);
            p.setRelevancyScore((double) result[2]);

            responseDTO.setProducts(Collections.singletonList(p));
            responseDTOs.add(responseDTO);
        }
        return new PageImpl<>(responseDTOs, pageable, resultsPage.getTotalElements());
    }
}
