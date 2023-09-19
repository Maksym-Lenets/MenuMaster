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
public class MenuDto {
    private List<MainCourseDto> mainCourseList;
    private List<DessertDto> desserts;
    private List<DrinkDto> drinks;
}
