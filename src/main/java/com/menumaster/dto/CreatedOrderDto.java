package com.menumaster.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedOrderDto {
    private Long id;
    private List<LunchDto> lunches;
    private List<OrderedDrinkDto> drinks;
    @Positive
    @NotNull
    private Integer totalPrice;
}
