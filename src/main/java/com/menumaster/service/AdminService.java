package com.menumaster.service;

import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.entity.menu.CuisineOrigin;

public interface AdminService {
    /**
     * Service method to add or update a Cuisine Origin.
     *
     * @param cuisineOrigin The CuisineOrigin entity to be added or updated.
     * @return The saved CuisineOrigin entity.
     */
    CuisineOrigin addOrUpdateCuisineOrigin(CuisineOrigin cuisineOrigin);

    /**
     * Service method to add or update a Main Course.
     *
     * @param mainCourseDto The MainCourseDto containing the details of the Main
     *                      Course to be added or updated.
     * @return The saved MainCourseDto entity.
     */
    MainCourseDto addOrUpdateMainCourse(MainCourseDto mainCourseDto);

    /**
     * Service method to add or update a Dessert.
     *
     * @param dessertDto The DessertDto containing the details of the Dessert to be
     *                   added or updated.
     * @return The saved DessertDto entity.
     */
    DessertDto addOrUpdateDessert(DessertDto dessertDto);

    /**
     * Service method to add or update a Drink.
     *
     * @param drinkDto The DrinkDto containing the details of the Drink to be added
     *                 or updated.
     * @return The saved DrinkDto entity.
     */
    DrinkDto addOrUpdateDrink(DrinkDto drinkDto);
}
