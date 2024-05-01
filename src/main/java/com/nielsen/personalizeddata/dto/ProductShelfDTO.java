package com.nielsen.personalizeddata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductShelfDTO {

    @NotNull(message = "Product ID must not be null")
    private String productId;

    @NotNull(message = "Relevancy score must not be null")
    private double relevancyScore;
}
