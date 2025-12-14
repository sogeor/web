package com.sogeor.web.service;

import com.sogeor.web.dto.CartDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class CartService {

    private final WebClient webClient;

    private final String cartServiceUrl;

    public CartService(@Qualifier("userWebClient") WebClient webClient,
                       @Value("${sogeor.services.cart}") String cartServiceUrl) {
        this.webClient = webClient;
        this.cartServiceUrl = cartServiceUrl;
    }

    public Mono<CartDto> getCart() {
        return webClient.get().uri(cartServiceUrl).retrieve().bodyToMono(CartDto.class);
    }

    public Mono<Void> addToCart(String productId, int quantity) {
        return webClient.post()
                        .uri(cartServiceUrl + "/add")
                        .bodyValue(Map.of("productId", productId, "quantity", quantity))
                        .retrieve()
                        .bodyToMono(Void.class);
    }

    public Mono<Void> updateCart(String productId, int quantity) {
        return webClient.put()
                        .uri(cartServiceUrl + "/update")
                        .bodyValue(Map.of("productId", productId, "quantity", quantity))
                        .retrieve()
                        .bodyToMono(Void.class);
    }

    public Mono<Void> removeFromCart(String productId) {
        return webClient.delete()
                        .uri(cartServiceUrl + "/remove/{productId}", productId)
                        .retrieve()
                        .bodyToMono(Void.class);
    }

    public Mono<Void> clearCart() {
        return webClient.delete().uri(cartServiceUrl + "/clear").retrieve().bodyToMono(Void.class);
    }

}
