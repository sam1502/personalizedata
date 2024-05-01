package com.nielsen.personalizeddata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShopperProductResponseDTO {

    String shopperId;
    List<PersonalizedProductDTO> products;
}
