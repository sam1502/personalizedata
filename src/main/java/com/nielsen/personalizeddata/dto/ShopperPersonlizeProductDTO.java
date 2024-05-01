package com.nielsen.personalizeddata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopperPersonlizeProductDTO {

    @NotNull(message = "Shopper ID must not be null")
    private String shopperId;

    private List<ProductShelfDTO> shelf;
}
