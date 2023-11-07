package com.amuk3lani.shop.inventory.order.service;

import com.amuk3lani.shop.inventory.order.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
