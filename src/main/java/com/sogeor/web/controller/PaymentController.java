package com.sogeor.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    @GetMapping("/{orderId}")
    public String paymentPage(@PathVariable String orderId, Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("requestPath", "/payment");
        return "payment";
    }

    @PostMapping("/confirm")
    public String confirmPayment(String orderId) {
        return "redirect:/orders/" + orderId;
    }

}
