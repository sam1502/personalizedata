package com.nielsen.personalizeddata.service;

import com.nielsen.personalizeddata.dto.ShopperProductResponseDTO;
import com.nielsen.personalizeddata.repository.ShopperPersonalizedProductRepository;
import com.nielsen.personalizeddata.repository.ShopperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class EcommerceServiceTest {

    @Spy
    @InjectMocks
    EcommerceService ecommerceService;

    @Mock
    ShopperPersonalizedProductRepository personalizedProductRepository;

    @Mock
    ShopperRepository shopperRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Get shopper product")
    public void testGetShopperProduct() {
        List<Object[]> temp = new ArrayList<>();
        Object[] obj = new Object[5];
        obj[0] = "MB-1234";
        obj[1] = "Adidas";
        obj[2] = 30.2342;
        obj[3] = null;

        temp.add(obj);

        Pageable pageable= PageRequest.of(0,10);
        Page<Object[]> result = new PageImpl<>(temp,pageable,1);


        when(personalizedProductRepository.findByShopperIdAndFilters("S-1000", null, null, pageable)).thenReturn(result);

        assertNotNull(ecommerceService.getShopperProducts("S-1000", null, null, 1, 10));
    }
}