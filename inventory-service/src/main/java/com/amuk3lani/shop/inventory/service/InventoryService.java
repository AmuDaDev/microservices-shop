package com.amuk3lani.shop.inventory.service;

import com.amuk3lani.shop.inventory.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> isInStock(List<String> skuCode);
}
