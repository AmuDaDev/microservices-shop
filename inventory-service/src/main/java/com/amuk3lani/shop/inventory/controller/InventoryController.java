package com.amuk3lani.shop.inventory.controller;

import com.amuk3lani.shop.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("sku-code") String skuCode) {
        boolean inStock = inventoryService.isInStock(skuCode);
        return new ResponseEntity<>(inStock,HttpStatus.OK);
    }
}
