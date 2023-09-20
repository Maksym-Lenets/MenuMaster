package com.menumaster.controller;

import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.entity.menu.CuisineOrigin;
import com.menumaster.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Controller", description = "API for administrator to support menu items")
public class AdminController {
    private final AdminService adminService;

    /**
     * Add or update a Cuisine Origin.
     *
     * @param cuisineOrigin The CuisineOrigin instance containing the data to be
     *                      saved
     * @return ResponseEntity containing the added or updated Cuisine Origin
     */
    @Operation(
        summary = "Add or update a Cuisine Origin",
        description = "returns saved Cuisine Origin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cuisine Origin added or updated successfully"),
        @ApiResponse(responseCode = "404", description = "Invalid CuisineOrigin id"),
        @ApiResponse(responseCode = "400", description = "Exception during new Cuisine Origin validating")
    })
    @PostMapping("/addCuisineOrigin")
    public ResponseEntity<CuisineOrigin> addOrUpdateCuisineOrigin(@RequestBody @Valid CuisineOrigin cuisineOrigin) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(adminService.addOrUpdateCuisineOrigin(cuisineOrigin));
    }

    /**
     * Add or update a Main Course.
     *
     * @param mainCourseDto The MainCourseDto instance containing the data to be
     *                      saved
     * @return ResponseEntity containing the added or updated Main Course
     */
    @Operation(
        summary = "Add or update a Main Course",
        description = "returns saved Main Course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Main Course added or updated successfully"),
        @ApiResponse(responseCode = "404", description = "Invalid MainCourse id"),
        @ApiResponse(responseCode = "406",
            description = "At least one of the required fields of the new item is not filled in")
    })
    @PostMapping("/addMainCourse")
    public ResponseEntity<MainCourseDto> addOrUpdateMainCourse(@RequestBody MainCourseDto mainCourseDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(adminService.addOrUpdateMainCourse(mainCourseDto));
    }

    /**
     * Add or update a Dessert.
     *
     * @param dessertDto The DessertDto instance containing the data to be saved
     * @return ResponseEntity containing the added or updated Dessert
     */
    @Operation(
        summary = "Add or update a Dessert",
        description = "returns saved Dessert")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Dessert added or updated successfully"),
        @ApiResponse(responseCode = "404", description = "Invalid Dessert id"),
        @ApiResponse(responseCode = "406",
            description = "At least one of the required fields of the new item is not filled in")
    })
    @PostMapping("/addDessert")
    public ResponseEntity<DessertDto> addOrUpdateDessert(@RequestBody DessertDto dessertDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(adminService.addOrUpdateDessert(dessertDto));
    }

    /**
     * Add or update a Drink.
     *
     * @param drinkDto The DrinkDto instance containing the data to be saved
     * @return ResponseEntity containing the added or updated Drink
     */
    @Operation(
        summary = "Add or update a Drink",
        description = "returns saved Drink")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Drink added or updated successfully"),
        @ApiResponse(responseCode = "404", description = "Invalid Drink id"),
        @ApiResponse(responseCode = "406",
            description = "At least one of the required fields of the new item is not filled in")
    })
    @PostMapping("/addDrink")
    public ResponseEntity<DrinkDto> addOrUpdateDrink(@RequestBody DrinkDto drinkDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(adminService.addOrUpdateDrink(drinkDto));
    }
}
