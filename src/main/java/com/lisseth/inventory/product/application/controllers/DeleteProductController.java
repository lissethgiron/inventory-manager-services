package com.lisseth.inventory.product.application.controllers;

import com.lisseth.inventory.common.application.controllers.ResponseController;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.product.domain.ports.input.DeleteProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.lisseth.inventory.common.application.controllers.util.JwtUtil.checkAuth;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteProductController {
    private final DeleteProductServicePort deleteProductService;

    @Operation(summary = "Service to delete products by id")
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<JsonApiResponse<Boolean>> deleteProduct(
            @RequestHeader(value="Authorization") String authorization,
            @PathVariable("productId") @Valid String productId
    ) {
        checkAuth(authorization);
        Boolean response = deleteProductService.delete(productId);
        log.info("product deleted: {}, {}", response, productId);
        return ResponseController.success("product", productId, response);
    }
}
