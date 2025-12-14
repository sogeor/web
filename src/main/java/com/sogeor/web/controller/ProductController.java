package com.sogeor.web.controller;

import com.sogeor.web.dto.ProductDetailDto;
import com.sogeor.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("requestPath", "/product");
        return "product";
    }

}

