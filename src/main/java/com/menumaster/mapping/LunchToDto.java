package com.menumaster.mapping;

import com.menumaster.dto.LunchDto;
import com.menumaster.entity.Lunch;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class LunchToDto extends AbstractConverter<Lunch, LunchDto> {
    @Override
    protected LunchDto convert(Lunch lunch) {
        return LunchDto.builder()
            .mainCourseId(lunch.getMainCourse().getId())
            .dessertId(lunch.getDessert().getId())
            .build();
    }
}
