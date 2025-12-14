package com.sogeor.web.controller;

import com.sogeor.web.dto.CartDto;
import com.sogeor.web.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String viewCart(Model model) {
        try {
            CartDto cart = cartService.getCart().block();
            model.addAttribute("cart", cart);
        } catch (Exception e) {
            model.addAttribute("cart", new CartDto());
        }
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam String productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity).block();
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam String productId, @RequestParam int quantity) {
        cartService.updateCart(productId, quantity).block();
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable String productId) {
        cartService.removeFromCart(productId).block();
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart().block();
        return "redirect:/cart";
    }

}
