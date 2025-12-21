package com.sogeor.web.controller;

import com.sogeor.web.dto.PageResponse;
import com.sogeor.web.dto.Product;
import com.sogeor.web.dto.ProductDto;
import com.sogeor.web.service.ProductService;
import com.sogeor.web.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since 1.0.0
 */
//@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
class SearchController {

    private final ProductsService service;

    @GetMapping
    public String search(@RequestParam String query, @RequestParam(defaultValue = "16") int limit,
                         Model model) {
        PageResponse<Product> results = new PageResponse<>();
//        results.setContent(service.searchProduct(query,  limit).blockFirst());

        model.addAttribute("products", service.searchProduct(query,  limit).blockFirst());
        model.addAttribute("query", query);
//        model.addAttribute("category", category);
        model.addAttribute("requestPath", "/search");
        return "search";
    }

}
