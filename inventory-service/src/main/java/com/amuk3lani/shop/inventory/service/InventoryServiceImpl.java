package com.amuk3lani.shop.inventory.service;

import com.amuk3lani.shop.inventory.dto.InventoryDTO;
import com.amuk3lani.shop.inventory.model.Inventory;
import com.amuk3lani.shop.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryDTO> isInStock(List<String> skuCode) {
        log.info("Checking Inventory");
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCode);
        return inventories.stream()
                .map(inventory ->
                        InventoryDTO.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
