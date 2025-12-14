package com.sogeor.web.service;

import com.sogeor.web.dto.OrderDto;
import com.sogeor.web.dto.PageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class OrderService {

    private final WebClient webClient;

    private final String orderServiceUrl;

    public OrderService(@Qualifier("userWebClient") WebClient webClient,
                        @Value("${sogeor.services.orders}") String orderServiceUrl) {
        this.webClient = webClient;
        this.orderServiceUrl = orderServiceUrl;
    }

    public Mono<PageResponse<OrderDto>> getOrders(int page, int size) {
        return webClient.get()
                        .uri(orderServiceUrl + "?page={page}&size={size}", page, size)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<PageResponse<OrderDto>>() {});
    }

    public Mono<OrderDto> getOrderById(String id) {
        return webClient.get().uri(orderServiceUrl + "/{id}", id).retrieve().bodyToMono(OrderDto.class);
    }

    public Mono<OrderDto> createOrder(String shippingAddress, String paymentMethod) {
        return webClient.post()
                        .uri(orderServiceUrl + "/create")
                        .bodyValue(Map.of("shippingAddress", shippingAddress, "paymentMethod", paymentMethod))
                        .retrieve()
                        .bodyToMono(OrderDto.class);
    }

    public Mono<Void> cancelOrder(String id) {
        return webClient.delete() // Prompt says DELETE /v1/orders/{id}/cancel
                        .uri(orderServiceUrl + "/{id}/cancel", id).retrieve().bodyToMono(Void.class);
    }

}
