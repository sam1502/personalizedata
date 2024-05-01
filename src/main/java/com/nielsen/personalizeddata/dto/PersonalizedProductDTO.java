package com.nielsen.personalizeddata.dto;

import com.nielsen.personalizeddata.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalizedProductDTO extends Product {
    private double relevancyScore;
}
