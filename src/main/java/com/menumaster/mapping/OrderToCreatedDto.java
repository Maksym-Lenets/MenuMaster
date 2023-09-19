package com.menumaster.mapping;

import com.menumaster.dto.CreatedOrderDto;
import com.menumaster.entity.Order;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class OrderToCreatedDto extends AbstractConverter<Order, CreatedOrderDto> {
    @Override
    protected CreatedOrderDto convert(Order order) {
        return CreatedOrderDto.builder()
            .id(order.getId())
            .totalPrice(countTotalPrice(order))
            .build();
    }

    private int countTotalPrice(Order order) {
        int totalPrice = 0;
        for (var lunch : order.getLunches()) {
            totalPrice += lunch.getDessert().getPrice();
            totalPrice += lunch.getMainCourse().getPrice();
        }

        for (var orderedDrink : order.getDrinks()) {
            totalPrice += orderedDrink.getDrink().getPrice();
        }

        return totalPrice;
    }
}
