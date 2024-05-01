package com.nielsen.personalizeddata.controller;

import com.nielsen.personalizeddata.dto.ShopperProductResponseDTO;
import com.nielsen.personalizeddata.service.EcommerceService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ECommerceControllerTest {

    @Spy
    @InjectMocks
    ECommerceController eCommerceController;

    @Mock
    EcommerceService ecommerceService;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eCommerceController).build();
    }

    @Test
    @DisplayName("E-commerce controller")
     void testEcommerceController() throws Exception {

        List<ShopperProductResponseDTO> responseDTOs = new ArrayList<>();

        Pageable pageable= PageRequest.of(0,5);
        Page<ShopperProductResponseDTO> result = new PageImpl<>(responseDTOs,pageable,1);

        when(ecommerceService.getShopperProducts("S-1000", null, null, 1, 10))
                .thenReturn(result);

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/external/shoppers/{shopperId}/products", "S-1000"))
                .andExpect(status().isNoContent());

        verify(ecommerceService, times(1)).getShopperProducts("S-1000", null, null, 1, 10);
    }
}