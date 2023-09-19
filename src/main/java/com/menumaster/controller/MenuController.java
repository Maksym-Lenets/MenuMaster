package com.menumaster.controller;

import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.dto.MenuDto;
import com.menumaster.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Tag(name = "Menu Controller", description = "Menu API for customer")
public class MenuController {
    private final MenuService menuService;

    /**
     * Retrieve all menu items.
     *
     * @return ResponseEntity containing the list of all menu items.
     */
    @Operation(
        summary = "Get all menu items",
        description = "returns all available food and drinks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/allMenuItems")
    public ResponseEntity<MenuDto> getFullMenu() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(menuService.getMenu());
    }

    /**
     * Retrieve all drinks from the menu.
     *
     * @return ResponseEntity containing the list of all drinks.
     */
    @Operation(
        summary = "Get all drinks from the menu",
        description = "returns all available drinks")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/drinks")
    public ResponseEntity<List<DrinkDto>> getAllDrinks() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(menuService.getDrinks());
    }

    /**
     * Retrieve all main courses from the menu.
     *
     * @return ResponseEntity containing the list of all main courses.
     */
    @Operation(
        summary = "Get all main courses from the menu",
        description = "returns all available main courses")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/mainCourses")
    public ResponseEntity<List<MainCourseDto>> getAllMainCourses() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(menuService.getMainCourses());
    }

    /**
     * Retrieve all desserts from the menu.
     *
     * @return ResponseEntity containing the list of all desserts.
     */
    @Operation(
        summary = "Get all desserts from the menu",
        description = "returns all available desserts")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/desserts")
    public ResponseEntity<List<DessertDto>> getAllDesserts() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(menuService.getDesserts());
    }
}
