package com.sogeor.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {

    private String productId;

    private String productName;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal total;

}
