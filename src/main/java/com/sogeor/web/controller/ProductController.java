package com.sogeor.web.controller;

import com.sogeor.web.dto.PageResponse;
import com.sogeor.web.dto.ProductDetailDto;
import com.sogeor.web.dto.ProductDto;
import com.sogeor.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public String productDetail(@PathVariable String id, Model model) {
        ProductDetailDto product = productService.getProductDetail(id).block();
        model.addAttribute("product", product.getProduct());
        model.addAttribute("availableQuantity", product.getAvailableQuantity());
        return "product";
    }

}

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
        return "search";
    }

}
