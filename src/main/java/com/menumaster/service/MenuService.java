package com.menumaster.service;

import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.dto.MenuDto;

import java.util.List;

public interface MenuService {
    /**
     * Service method to retrieve the entire menu, including Main Courses, Desserts,
     * and Drinks.
     *
     * @return A MenuDto containing lists of MainCourseDto, DessertDto, and
     *         DrinkDto.
     */
    MenuDto getMenu();

    /**
     * Service method to retrieve a list of MainCourseDto objects.
     *
     * @return A list of MainCourseDto objects.
     */
    List<MainCourseDto> getMainCourses();

    /**
     * Service method to retrieve a list of DessertDto objects.
     *
     * @return A list of DessertDto objects.
     */
    List<DessertDto> getDesserts();

    /**
     * Service method to retrieve a list of DrinkDto objects.
     *
     * @return A list of DrinkDto objects.
     */
    List<DrinkDto> getDrinks();
}
