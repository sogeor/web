package com.sogeor.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDto {

    private String userId;

    private List<CartItemDto> items;

    private BigDecimal totalAmount;

}
