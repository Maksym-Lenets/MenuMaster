package com.menumaster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDto {
    private List<LunchDto> lunches;
    private List<OrderedDrinkDto> drinks;
}
