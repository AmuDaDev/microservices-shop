package com.amuk3lani.shop.inventory.order.service;

import com.amuk3lani.shop.inventory.order.model.OrderLineItems;
import com.amuk3lani.shop.inventory.order.repository.OrderRepository;
import com.amuk3lani.shop.inventory.order.dto.OrderLineItemsDto;
import com.amuk3lani.shop.inventory.order.dto.OrderRequest;
import com.amuk3lani.shop.inventory.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToEntity)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToEntity(OrderLineItemsDto orderLineItemsDto) {
        return mapper.map(orderLineItemsDto,OrderLineItems.class);
    }
}
