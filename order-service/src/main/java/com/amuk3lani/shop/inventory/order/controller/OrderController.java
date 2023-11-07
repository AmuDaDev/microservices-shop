package com.amuk3lani.shop.inventory.order.controller;

import com.amuk3lani.shop.inventory.order.dto.OrderRequest;
import com.amuk3lani.shop.inventory.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>("Order Placed Successfully",HttpStatus.CREATED);
    }
}
