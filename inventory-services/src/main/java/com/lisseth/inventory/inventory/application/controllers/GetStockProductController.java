package com.lisseth.inventory.inventory.application.controllers;

import com.lisseth.inventory.common.application.controllers.ResponseController;
import com.lisseth.inventory.common.application.models.JsonApiResponse;
import com.lisseth.inventory.inventory.application.models.InventoryResponse;
import com.lisseth.inventory.inventory.domain.ports.input.GetStockProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.lisseth.inventory.common.application.controllers.util.JwtUtil.checkAuth;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetStockProductController {

    private final GetStockProductServicePort getStockProductService;

    @Operation(summary = "Service to get stock by productId.")
    @GetMapping("/stock/{productId}")
    public ResponseEntity<JsonApiResponse<InventoryResponse>> getStock(
            @RequestHeader(value="Authorization") String authorization,
            @PathVariable("productId") @Valid String productId
    ) {
        checkAuth(authorization);
        InventoryResponse stock = getStockProductService.get(productId, authorization);
        log.debug("stock {}", stock.toString());
        return ResponseController.success("Inventory", stock.getProductId(), stock);
    }
}
