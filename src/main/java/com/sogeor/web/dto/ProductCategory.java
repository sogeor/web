package com.sogeor.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @since 1.0.0-RC1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

    /**
     * @since 1.0.0-RC1
     */
    private String uuid;

    /**
     * @since 1.0.0-RC1
     */
    private String name;

    /**
     * @since 1.0.0-RC1
     */
    private String description;

}
