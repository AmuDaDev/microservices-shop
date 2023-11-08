package com.amuk3lani.shop.inventory.controller;

import com.amuk3lani.shop.inventory.dto.InventoryDTO;
import com.amuk3lani.shop.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> isInStock(@RequestParam List<String> skuCode) {
        List<InventoryDTO> inventoryList = inventoryService.isInStock(skuCode);
        return ResponseEntity.ok(inventoryList);
    }
}
