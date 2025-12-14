package com.sogeor.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {

    private String id;

    private String userId;

    private String status;

    private LocalDateTime createdAt;

    private BigDecimal totalAmount;

    private String shippingAddress;

    private String paymentMethod;

}
