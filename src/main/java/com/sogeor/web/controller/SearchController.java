package com.sogeor.web.controller;

import com.sogeor.web.dto.PageResponse;
import com.sogeor.web.dto.ProductDto;
import com.sogeor.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @since 1.0.0
 */
@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
class SearchController {

    private final ProductService productService;

    @GetMapping
    public String search(@RequestParam String query, @RequestParam(required = false) String category,
                         @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size,
                         Model model) {
        PageResponse<ProductDto> results = productService.searchProducts(query, category, page, size).block();
        model.addAttribute("products", results);
        model.addAttribute("query", query);
        model.addAttribute("category", category);
        model.addAttribute("requestPath", "/search");
        return "search";
    }

}
