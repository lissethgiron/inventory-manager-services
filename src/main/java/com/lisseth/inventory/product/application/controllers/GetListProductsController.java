package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.controllers.ResponseController;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.application.models.ProductResponse;
import com.lisseth.inventory.product.domain.ports.input.GetListProductsServicePort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetListProductsController {

    private final GetListProductsServicePort getListProductsService;

    @Operation(summary = "Service to get products list")
    @GetMapping("/products")
    public ResponseEntity<JsonApiResponse<List<ProductResponse>>> getProduct(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        Page<ProductResponse> productsPage = getListProductsService.getAll(page, pageSize);
        List<ProductResponse> products = productsPage.getContent();
        List<String> ids = products.stream()
                .map(ProductResponse::getProductId)
                .toList();

        Map<String, Object> meta = Map.of(
                "currentPage", productsPage.getNumber(),
                "totalItems", productsPage.getTotalElements(),
                "totalPages", productsPage.getTotalPages(),
                "pageSize", productsPage.getSize()
        );

        return ResponseController.successList("product", ids, products, meta);
    }
}
