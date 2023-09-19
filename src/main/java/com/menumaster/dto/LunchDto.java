package com.menumaster.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LunchDto {
    @Positive
    @NotNull
    private Long mainCourseId;
    @Positive
    @NotNull
    private Long dessertId;
}
