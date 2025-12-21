package com.sogeor.web.controller;

import com.sogeor.web.dto.Product;
import com.sogeor.web.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService service;

    @GetMapping("/{uuid}")
    public String productDetail(@PathVariable String uuid, Model model) {
        Product product = service.getProduct(uuid).block();
        model.addAttribute("product", product);
        model.addAttribute("availableQuantity", 0);
        model.addAttribute("requestPath", "/product");
        return "product";
    }

}

