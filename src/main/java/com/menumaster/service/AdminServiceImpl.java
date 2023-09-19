package com.menumaster.service;

import com.menumaster.constatnt.ErrorMessage;
import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.entity.menu.CuisineOrigin;
import com.menumaster.entity.menu.Dessert;
import com.menumaster.entity.menu.Drink;
import com.menumaster.entity.menu.MainCourse;
import com.menumaster.exception.ItemNotFoundException;
import com.menumaster.repository.CuisineOriginRepository;
import com.menumaster.repository.DessertRepository;
import com.menumaster.repository.DrinkRepository;
import com.menumaster.repository.MainCourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
    private final CuisineOriginRepository cuisineOriginRepository;
    private final MainCourseRepository mainCourseRepository;
    private final DessertRepository dessertRepository;
    private final DrinkRepository drinkRepository;
    private final ModelMapper modelMapper;

    @Override
    public CuisineOrigin addOrUpdateCuisineOrigin(CuisineOrigin cuisineOrigin) {
        if (isIdValid(cuisineOrigin.getId())) {
            CuisineOrigin savedCuisineOrigin = cuisineOriginRepository.findById(cuisineOrigin.getId())
                .orElseThrow(
                    () -> new ItemNotFoundException(ErrorMessage.CUISINE_ORIGIN_NOT_FOUND + cuisineOrigin.getId()));
            savedCuisineOrigin.setDescription(cuisineOrigin.getDescription());
            return savedCuisineOrigin;
        } else {
            cuisineOrigin.setId(null);
            return cuisineOriginRepository.save(cuisineOrigin);
        }
    }

    @Override
    public MainCourseDto addOrUpdateMainCourse(MainCourseDto mainCourseDto) {
        MainCourse mainCourse = new MainCourse();
        if (isIdValid(mainCourseDto.getId())) {
            mainCourse = mainCourseRepository.findById(mainCourseDto.getId())
                .orElseThrow(
                    () -> new ItemNotFoundException(ErrorMessage.MAIN_COURSE_NOT_FOUND + mainCourseDto.getId()));
        }

        if (Objects.nonNull(mainCourseDto.getName())) {
            mainCourse.setName(mainCourseDto.getName());
        }
        if (Objects.nonNull(mainCourseDto.getPrice()) && mainCourseDto.getPrice() >= 0) {
            mainCourse.setPrice(mainCourseDto.getPrice());
        }
        if (Objects.nonNull(mainCourseDto.getCuisineOrigin())) {
            CuisineOrigin cuisineOrigin = cuisineOriginRepository.findByDescription(mainCourseDto.getCuisineOrigin())
                .orElseThrow(() -> new ItemNotFoundException(
                    ErrorMessage.CUISINE_ORIGIN_NOT_FOUND_BY_DESCRIPTION + mainCourseDto.getCuisineOrigin()));
            mainCourse.setCuisine(cuisineOrigin);
        }
        mainCourse = mainCourseRepository.save(mainCourse);
        return modelMapper.map(mainCourse, MainCourseDto.class);
    }

    @Override
    public DessertDto addOrUpdateDessert(DessertDto dessertDto) {
        Dessert dessert = new Dessert();
        if (isIdValid(dessertDto.getId())) {
            dessert = dessertRepository.findById(dessertDto.getId())
                .orElseThrow(() -> new ItemNotFoundException(ErrorMessage.DESSERT_NOT_FOUND + dessertDto.getId()));
        }

        if (Objects.nonNull(dessertDto.getName())) {
            dessert.setName(dessertDto.getName());
        }
        if (Objects.nonNull(dessertDto.getPrice()) && dessertDto.getPrice() >= 0) {
            dessert.setPrice(dessertDto.getPrice());
        }
        if (Objects.nonNull(dessertDto.getCuisineDescription())) {
            CuisineOrigin cuisineOrigin = cuisineOriginRepository.findByDescription(dessertDto.getCuisineDescription())
                .orElseThrow(() -> new ItemNotFoundException(
                    ErrorMessage.CUISINE_ORIGIN_NOT_FOUND_BY_DESCRIPTION + dessertDto.getCuisineDescription()));
            dessert.setCuisine(cuisineOrigin);
        }
        dessert = dessertRepository.save(dessert);
        return modelMapper.map(dessert, DessertDto.class);
    }

    @Override
    public DrinkDto addOrUpdateDrink(DrinkDto drinkDto) {
        Drink drink = new Drink();
        if (isIdValid(drinkDto.getId())) {
            drink = drinkRepository.findById(drinkDto.getId())
                .orElseThrow(() -> new ItemNotFoundException(ErrorMessage.DRINK_NOT_FOUND + drinkDto.getId()));
        }
        if (Objects.nonNull(drinkDto.getName())) {
            drink.setName(drinkDto.getName());
        }
        if (Objects.nonNull(drinkDto.getPrice()) && drinkDto.getPrice() >= 0) {
            drink.setPrice(drinkDto.getPrice());
        }

        drink = drinkRepository.save(drink);
        return modelMapper.map(drink, DrinkDto.class);
    }

    private boolean isIdValid(Long id) {
        return id != null && id > 0;
    }
}
