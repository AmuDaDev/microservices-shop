package com.amuk3lani.shop.inventory.order.service;

import com.amuk3lani.shop.inventory.order.dto.InventoryDTO;
import com.amuk3lani.shop.inventory.order.exception.ResourceNotFoundException;
import com.amuk3lani.shop.inventory.order.model.OrderLineItems;
import com.amuk3lani.shop.inventory.order.repository.OrderRepository;
import com.amuk3lani.shop.inventory.order.dto.OrderLineItemsDto;
import com.amuk3lani.shop.inventory.order.dto.OrderRequest;
import com.amuk3lani.shop.inventory.order.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private final WebClient webClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItemsDto> lineItems = orderRequest.getOrderLineItemsDtoList();

        //Call Inventory to check stock
        List<String> skuCodes = lineItems.stream()
                .map(OrderLineItemsDto::getSkuCode)
                .toList();

        InventoryDTO[] inventoryStockArray = webClient.get()
                .uri("http://localhost:8002/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryDTO[].class)
                .block();

        //Check if all items are in stock
        if (inventoryStockArray != null) {
            boolean allItemsInStock = Arrays.stream(inventoryStockArray)
                    .allMatch(InventoryDTO::isInStock);

            if(allItemsInStock){
                List<OrderLineItems> orderLineItems = lineItems.stream()
                        .map(this::mapToEntity)
                        .toList();

                order.setOrderLineItemsList(orderLineItems);
                log.info("Saving {} orders",order.getOrderLineItemsList().size());
                orderRepository.save(order);
            } else {
                throw new ResourceNotFoundException("Order","skuCode",0);
            }
        }else {
            throw new ResourceNotFoundException("Order","skuCode",0);
        }
    }

    private OrderLineItems mapToEntity(OrderLineItemsDto orderLineItemsDto) {
        return mapper.map(orderLineItemsDto,OrderLineItems.class);
    }
}
