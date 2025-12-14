package com.sogeor.web.service;

import com.sogeor.web.dto.InventoryDto;
import com.sogeor.web.dto.PageResponse;
import com.sogeor.web.dto.ProductDetailDto;
import com.sogeor.web.dto.ProductDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {

    private final WebClient webClient;

    private final String productServiceUrl;

    private final String inventoryServiceUrl;

    public ProductService(@Qualifier("system") WebClient webClient,
                          @Value("${sogeor.services.products}") String productServiceUrl,
                          @Value("${sogeor.services.inventory}") String inventoryServiceUrl) {
        this.webClient = webClient;
        this.productServiceUrl = productServiceUrl;
        this.inventoryServiceUrl = inventoryServiceUrl;
    }

    public Mono<@NotNull List<ProductDto>> getAllProducts(int page, int size) {
        return webClient.get()
                        .uri(productServiceUrl + "?page={page}&size={size}", page, size)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    public Mono<ProductDetailDto> getProductDetail(String id) {
        Mono<ProductDto> productMono = webClient.get()
                                                .uri(productServiceUrl + "/{id}", id)
                                                .retrieve()
                                                .bodyToMono(ProductDto.class);

        Mono<InventoryDto> inventoryMono = webClient.get()
                                                    .uri(inventoryServiceUrl + "/{id}", id)
                                                    .retrieve()
                                                    .bodyToMono(InventoryDto.class)
                                                    .onErrorResume(e -> Mono.just(new InventoryDto()));
        // 404

        return Mono.zip(productMono, inventoryMono, (product, inventory) -> {
            ProductDetailDto detail = new ProductDetailDto();
            detail.setProduct(product);
            detail.setAvailableQuantity(inventory.getQuantity() != null ? inventory.getQuantity() : 0);
            return detail;
        });
    }

    public Mono<PageResponse<ProductDto>> searchProducts(String query, String category, int page, int size) {
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path(productServiceUrl + "/search")
                                                     .queryParam("query", query)
                                                     .queryParam("category", category)
                                                     .queryParam("page", page)
                                                     .queryParam("size", size)
                                                     .build())
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<PageResponse<ProductDto>>() {});
    }

    public Mono<@NotNull Long> getNumberOfProducts() {
        return webClient.get().uri(productServiceUrl + "/numberOfProducts").retrieve().bodyToMono(Long.class);
    }

    public Mono<@NotNull Long> getNumberOfPages(int pageSize) {
        return webClient.get()
                        .uri(productServiceUrl + "/numberOfPages?size={size}", pageSize)
                        .retrieve()
                        .bodyToMono(Long.class);
    }

}
