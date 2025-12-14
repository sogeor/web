package com.sogeor.web.controller;

import com.sogeor.web.dto.PageResponse;
import com.sogeor.web.dto.ProductDto;
import com.sogeor.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size,
                       Model model) {
        List<ProductDto> products = productService.getAllProducts(page, size).block();

        PageResponse<ProductDto> pageResponse = new PageResponse<>();
        pageResponse.setContent(products);
        pageResponse.setTotalPages(page + 3);
        pageResponse.setTotalElements(-1);
        pageResponse.setNumber(page);
        pageResponse.setSize(size);

        model.addAttribute("products", pageResponse);
        return "index";
    }

}
