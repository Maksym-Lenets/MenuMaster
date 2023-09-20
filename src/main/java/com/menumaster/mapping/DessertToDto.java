package com.menumaster.mapping;

import com.menumaster.dto.DessertDto;
import com.menumaster.entity.menu.Dessert;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class DessertToDto extends AbstractConverter<Dessert, DessertDto> {
    @Override
    protected DessertDto convert(Dessert dessert) {
        return DessertDto.builder()
            .id(dessert.getId())
            .name(dessert.getName())
            .price(dessert.getPrice())
            .cuisineOrigin(dessert.getCuisine().getDescription())
            .build();
    }
}
