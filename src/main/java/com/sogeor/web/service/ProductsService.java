package com.sogeor.web.service;

import com.sogeor.web.dto.Product;
import com.sogeor.web.dto.ProductCategory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @since 1.0.0-RC1
 */
@Service
public class ProductsService {

    /**
     * @since 1.0.0-RC1
     */
    private final WebClient webClient;

    /**
     * @since 1.0.0-RC1
     */
    private final String productServiceUrl;

    /**
     * @since 1.0.0-RC1
     */
    public ProductsService(@Qualifier("system") WebClient webClient,
                           @Value("${sogeor.services.products}") String productServiceUrl) {
        this.webClient = webClient;
        this.productServiceUrl = productServiceUrl;
    }

    /**
     * @since 1.0.0-RC1
     */
    public Mono<@NotNull ProductCategory> getCategory(String uuid) {
        return webClient.get()
                        .uri(productServiceUrl + "/category/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(ProductCategory.class);
    }

    /**
     * @since 1.0.0-RC1
     */
    public Flux<@NotNull ProductCategory> searchCategory(String name, int limit) {
        return webClient.get()
                        .uri(productServiceUrl + "/category/search?name={name}&limit={limit}", name, limit)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(ProductCategory.class);
    }

    /**
     * @since 1.0.0-RC1
     */
    public Mono<@NotNull Long> getCategoryCount() {
        return webClient.get().uri(productServiceUrl + "/category/about").retrieve().bodyToMono(Long.class);
    }

    /**
     * @since 1.0.0-RC1
     */
    public Mono<@NotNull Product> getProduct(String uuid) {
        return webClient.get()
                        .uri(productServiceUrl + "/product/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Product.class);
    }

    /**
     * @since 1.0.0-RC1
     */
    public Flux<@NotNull Product> getProduct(int page, int count) {
        return webClient.get()
                        .uri(productServiceUrl + "/product/?page={page}&count={count}", page, count)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(Product.class);
    }

    /**
     * @since 1.0.0-RC1
     */
    public Flux<@NotNull Product> getProduct(String category, int page, int count) {
        return category != null ? webClient.get()
                                           .uri(productServiceUrl +
                                                "/product/?category={category}&page={page}&count={count}", category,
                                                page, count)
                                           .accept(MediaType.APPLICATION_JSON)
                                           .retrieve()
                                           .bodyToFlux(Product.class) : getProduct(page, count);
    }

    /**
     * @since 1.0.0-RC1
     */
    public Flux<@NotNull Product> searchProduct(String name, int limit) {
        return webClient.get()
                        .uri(productServiceUrl + "/product/search?name={name}&limit={limit}", name, limit)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToFlux(Product.class);
    }

    /**
     * @since 1.0.0-RC1
     */
    public Mono<@NotNull Long> getProductCount() {
        return webClient.get().uri(productServiceUrl + "/product/about").retrieve().bodyToMono(Long.class);
    }

}
