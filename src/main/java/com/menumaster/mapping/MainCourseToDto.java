package com.menumaster.mapping;

import com.menumaster.dto.MainCourseDto;
import com.menumaster.entity.menu.MainCourse;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class MainCourseToDto extends AbstractConverter<MainCourse, MainCourseDto> {
    @Override
    protected MainCourseDto convert(MainCourse mainCourse) {
        return MainCourseDto.builder()
            .id(mainCourse.getId())
            .name(mainCourse.getName())
            .price(mainCourse.getPrice())
            .cuisineOrigin(mainCourse.getCuisine().getDescription())
            .build();
    }
}
