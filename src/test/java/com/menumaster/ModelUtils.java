package com.menumaster;

import com.menumaster.dto.*;
import com.menumaster.entity.Lunch;
import com.menumaster.entity.Order;
import com.menumaster.entity.OrderedDrink;
import com.menumaster.entity.menu.CuisineOrigin;
import com.menumaster.entity.menu.Dessert;
import com.menumaster.entity.menu.Drink;
import com.menumaster.entity.menu.MainCourse;

import java.util.Arrays;
import java.util.List;

public class ModelUtils {
    public static CuisineOrigin getNewCuisineOrigin() {
        return CuisineOrigin.builder()
            .description("newCuisine")
            .build();
    }

    public static CuisineOrigin getSavedNewCuisineOrigin() {
        return CuisineOrigin.builder()
            .id(1L)
            .description("oldCuisine")
            .build();
    }

    public static CuisineOrigin getUpdatedCuisineOrigin() {
        return CuisineOrigin.builder()
            .id(1L)
            .description("newCuisine")
            .build();
    }

    public static List<CuisineOrigin> getCuisinesOrigin() {
        return Arrays.asList(CuisineOrigin.builder().id(1L).description("FirstCuisineOrigin").build(),
            CuisineOrigin.builder().id(1L).description("SecondCuisineOrigin").build(),
            CuisineOrigin.builder().id(1L).description("ThirdCuisineOrigin").build());

    }

    public static MainCourseDto getNewMainCourseDto() {
        return MainCourseDto.builder()
            .name("NewMeal")
            .price(200)
            .cuisineOrigin("newCuisine")
            .build();
    }

    public static MainCourseDto getUpdatedMainCourseDto() {
        return MainCourseDto.builder()
            .id(1L)
            .name("NewMeal")
            .price(200)
            .cuisineOrigin("newCuisine")
            .build();
    }

    public static MainCourse getSavedMainCourse() {
        return MainCourse.builder()
            .id(1L)
            .price(100)
            .name("OldMeal")
            .cuisine(CuisineOrigin.builder()
                .id(2L)
                .description("oldCuisine")
                .build())
            .build();
    }

    public static MainCourse getUpdatedMainCourse() {
        MainCourse mainCourse = new MainCourse();
        mainCourse.setId(1L);
        mainCourse.setPrice(200);
        mainCourse.setName("NewMeal");
        mainCourse.setCuisine(CuisineOrigin.builder()
            .id(1L)
            .description("newCuisine")
            .build());
        return mainCourse;
    }

    public static DessertDto getNewDessertDto() {
        return DessertDto.builder()
            .name("NewDessert")
            .price(50)
            .cuisineDescription("newCuisine")
            .build();
    }

    public static DessertDto getUpdatedDessertDto() {
        return DessertDto.builder()
            .id(1L)
            .name("NewDessert")
            .price(50)
            .cuisineDescription("newCuisine")
            .build();
    }

    public static Dessert getSavedDessert() {
        return Dessert.builder()
            .id(1L)
            .price(45)
            .name("OldDessert")
            .cuisine(CuisineOrigin.builder()
                .id(2L)
                .description("oldCuisine")
                .build())
            .build();
    }

    public static Dessert getUpdatedDessert() {
        Dessert dessert = new Dessert();
        dessert.setId(1L);
        dessert.setPrice(50);
        dessert.setName("NewDessert");
        dessert.setCuisine(CuisineOrigin.builder()
            .id(1L)
            .description("newCuisine")
            .build());
        return dessert;
    }

    public static DrinkDto getNewDrinkDto() {
        return DrinkDto.builder()
            .name("NewDrink")
            .price(25)
            .build();
    }

    public static DrinkDto getUpdatedDrinkDto() {
        return DrinkDto.builder()
            .id(1L)
            .name("NewDrink")
            .price(25)
            .build();
    }

    public static Drink getSavedDrink() {
        return Drink.builder()
            .id(1L)
            .name("OldDrink")
            .price(20)
            .build();
    }

    public static Drink getUpdatedDrink() {
        return Drink.builder()
            .id(1L)
            .name("NewDrink")
            .price(25)
            .build();
    }

    public static List<Drink> getDrinks() {
        return Arrays.asList(
            Drink.builder().id(1L).name("FirstDrink").price(100).build(),
            Drink.builder().id(2L).name("SecondDrink").price(200).build(),
            Drink.builder().id(3L).name("ThirdDrink").price(300).build());
    }

    public static List<DrinkDto> getDrinkDtos() {
        return Arrays.asList(
            DrinkDto.builder().id(1L).name("FirstDrink").price(100).build(),
            DrinkDto.builder().id(2L).name("SecondDrink").price(200).build(),
            DrinkDto.builder().id(3L).name("ThirdDrink").price(300).build());
    }

    public static List<MainCourse> getMainCourses() {
        var cuisines = getCuisinesOrigin();
        return Arrays.asList(
            MainCourse.builder().id(1L).name("FirstMainCourse").cuisine(cuisines.get(0)).price(100).build(),
            MainCourse.builder().id(2L).name("SecondMainCourse").cuisine(cuisines.get(1)).price(200).build(),
            MainCourse.builder().id(3L).name("ThirdMainCourse").cuisine(cuisines.get(2)).price(300).build());
    }

    public static List<MainCourseDto> getMainCourseDtos() {
        return Arrays.asList(
            MainCourseDto.builder().id(1L).name("FirstMainCourse").cuisineOrigin("FirstCuisineOrigin").price(100)
                .build(),
            MainCourseDto.builder().id(2L).name("SecondMainCourse").cuisineOrigin("SecondCuisineOrigin").price(200)
                .build(),
            MainCourseDto.builder().id(3L).name("ThirdMainCourse").cuisineOrigin("ThirdCuisineOrigin").price(300)
                .build());
    }

    public static List<Dessert> getDesserts() {
        var cuisines = getCuisinesOrigin();
        return Arrays.asList(
            Dessert.builder().id(1L).name("FirstDessert").cuisine(cuisines.get(0)).price(100).build(),
            Dessert.builder().id(2L).name("SecondDessert").cuisine(cuisines.get(1)).price(200).build(),
            Dessert.builder().id(3L).name("ThirdDessert").cuisine(cuisines.get(2)).price(300).build());
    }

    public static List<DessertDto> getDessertDtos() {
        return Arrays.asList(
            DessertDto.builder().id(1L).name("FirstDessert").cuisineDescription("FirstCuisineOrigin").price(100)
                .build(),
            DessertDto.builder().id(2L).name("SecondDessert").cuisineDescription("SecondCuisineOrigin").price(200)
                .build(),
            DessertDto.builder().id(3L).name("ThirdDessert").cuisineDescription("ThirdCuisineOrigin").price(300)
                .build());
    }

    public static NewOrderDto getOrderNewDto() {
        return NewOrderDto.builder()
            .lunches(List.of(
                LunchDto.builder()
                    .mainCourseId(1L)
                    .dessertId(2L)
                    .build(),
                LunchDto.builder()
                    .mainCourseId(3L)
                    .dessertId(4L)
                    .build()))
            .drinks(List.of(
                OrderedDrinkDto.builder()
                    .drinkId(5L)
                    .iceCubes(true)
                    .lemons(false)
                    .build(),
                OrderedDrinkDto.builder()
                    .drinkId(6L)
                    .iceCubes(false)
                    .lemons(true)
                    .build()))
            .build();
    }

    public static CreatedOrderDto getCreatedOrderDto() {
        return CreatedOrderDto.builder()
            .id(1L)
            .totalPrice(322)
            .lunches(List.of(
                LunchDto.builder()
                    .mainCourseId(1L)
                    .dessertId(2L)
                    .build(),
                LunchDto.builder()
                    .mainCourseId(3L)
                    .dessertId(4L)
                    .build()))
            .drinks(List.of(
                OrderedDrinkDto.builder()
                    .drinkId(5L)
                    .iceCubes(true)
                    .lemons(false)
                    .build(),
                OrderedDrinkDto.builder()
                    .drinkId(6L)
                    .iceCubes(false)
                    .lemons(true)
                    .build()))
            .build();
    }

    public static List<MainCourse> getSavedMainCourses() {
        MainCourse mainCourseFirstLunch = getSavedMainCourse();
        mainCourseFirstLunch.setId(1L);
        MainCourse mainCourseSecondLunch = getSavedMainCourse();
        mainCourseSecondLunch.setId(3L);
        return Arrays.asList(mainCourseFirstLunch, mainCourseSecondLunch);
    }

    public static List<Dessert> getSavedDesserts() {
        Dessert firstLunchDessert = getSavedDessert();
        firstLunchDessert.setId(2L);
        Dessert secondLunchDessert = getUpdatedDessert();
        secondLunchDessert.setId(4L);
        return Arrays.asList(firstLunchDessert, secondLunchDessert);
    }

    public static List<Drink> getSavedDrinks() {
        return Arrays.asList(Drink.builder().id(5L).name("Drink1").price(12).build(),
            Drink.builder().id(6L).name("Drink2").price(20).build());
    }

    public static Order getNewOrder() {
        var savedMainCourses = getSavedMainCourses();
        var savedDesserts = getSavedDesserts();
        var firstLunch = new Lunch(null, savedMainCourses.get(0), savedDesserts.get(0));
        var secondLunch = new Lunch(null, savedMainCourses.get(1), savedDesserts.get(1));
        var savedDrinks = getSavedDrinks();
        var firstOrderedDrink = OrderedDrink.builder()
            .drink(savedDrinks.get(0))
            .iceCubes(true)
            .lemons(false)
            .build();
        var secondOrderedDrink = OrderedDrink.builder()
            .drink(savedDrinks.get(0))
            .iceCubes(true)
            .lemons(false)
            .build();

        return Order.builder()
            .id(1L)
            .drinks(Arrays.asList(firstOrderedDrink, secondOrderedDrink))
            .lunches(Arrays.asList(firstLunch, secondLunch))
            .build();
    }

    public static MenuDto getMenuDto() {
        return MenuDto.builder()
            .mainCourseList(getMainCourseDtos())
            .desserts(getDessertDtos())
            .drinks(getDrinkDtos())
            .build();
    }

}
