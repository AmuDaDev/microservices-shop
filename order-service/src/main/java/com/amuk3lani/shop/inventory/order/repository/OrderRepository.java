package com.amuk3lani.shop.inventory.order.repository;

import com.amuk3lani.shop.inventory.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
