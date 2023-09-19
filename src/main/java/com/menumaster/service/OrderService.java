package com.menumaster.service;

import com.menumaster.dto.CreatedOrderDto;
import com.menumaster.dto.NewOrderDto;

public interface OrderService {
    /**
     * Service method to add a new order based on the provided OrderNewDto. It saves
     * the order along with any associated drinks and lunches.
     *
     * @param orderDto The OrderNewDto containing order details.
     * @return An OrderCreatedDto representing the newly created order.
     */
    CreatedOrderDto addNewOrder(NewOrderDto orderDto);
}
