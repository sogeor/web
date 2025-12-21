package com.sogeor.web.controller;

import com.sogeor.web.dto.PageResponse;
import com.sogeor.web.dto.Product;
import com.sogeor.web.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductsService service;

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size,
                       Model model) {
        final var productCount = Optional.ofNullable(service.getProductCount().block()).orElse(0L);
        final var products = service.getProduct(page, size).toStream();

        log.info("productCount: {}", productCount);
        log.info("products: {}", products);

        final var response = new PageResponse<Product>();
        response.setContent(products.toList());
        response.setTotalPages((int) Math.ceil((double) productCount / size));
        response.setTotalElements(productCount);
        response.setNumber(page);
        response.setSize(size);
        model.addAttribute("products", response);

        log.info("response: {}", response);

        model.addAttribute("requestPath", "/home");
        return "index";
    }


}
