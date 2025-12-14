package com.sogeor.web.dto;

import lombok.Data;

@Data
public class ProductDetailDto {

    private ProductDto product;

    private Integer availableQuantity;

}
