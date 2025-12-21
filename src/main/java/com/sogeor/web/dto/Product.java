package com.sogeor.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @since 1.0.0-RC1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * @since 1.0.0-RC1
     */
    private String uuid;

    /**
     * @since 1.0.0-RC1
     */
    private String category;

    /**
     * @since 1.0.0-RC1
     */
    private String name;

    /**
     * @since 1.0.0-RC1
     */
    private String description;

    /**
     * @since 1.0.0-RC1
     */
    private BigDecimal price;

}
