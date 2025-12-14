package com.sogeor.web.controller;

import com.sogeor.web.dto.OrderDto;
import com.sogeor.web.dto.PageResponse;
import com.sogeor.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String listOrders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                             Model model) {
        PageResponse<OrderDto> orders = orderService.getOrders(page, size).block();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/{id}")
    public String orderDetails(@PathVariable String id, Model model) {
        OrderDto order = orderService.getOrderById(id).block();
        model.addAttribute("order", order);
        return "order-details";
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam String shippingAddress, @RequestParam String paymentMethod) {
        OrderDto order = orderService.createOrder(shippingAddress, paymentMethod).block();
        return "redirect:/payment/" + order.getId();
    }

    @PostMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable String id) {
        orderService.cancelOrder(id).block();
        return "redirect:/orders";
    }

}
