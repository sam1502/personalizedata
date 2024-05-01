package com.nielsen.personalizeddata.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMetaDataDTO {

    private String productId;

    private String category;
    private String brand;

}
