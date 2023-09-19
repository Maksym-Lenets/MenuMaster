package com.menumaster.mapping;

import com.menumaster.dto.OrderedDrinkDto;
import com.menumaster.entity.OrderedDrink;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class OrderedDrinkToDto extends AbstractConverter<OrderedDrink, OrderedDrinkDto> {
    @Override
    protected OrderedDrinkDto convert(OrderedDrink orderedDrink) {
        return OrderedDrinkDto.builder()
            .drinkId(orderedDrink.getDrink().getId())
            .iceCubes(orderedDrink.isIceCubes())
            .iceCubes(orderedDrink.isLemons())
            .build();
    }
}