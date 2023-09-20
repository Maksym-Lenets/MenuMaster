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

import static java.util.Objects.*;
import static org.apache.logging.log4j.util.Strings.*;

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
        CuisineOrigin updatableCuisineOrigin = new CuisineOrigin();
        if (isIdValid(cuisineOrigin.getId())) {
            updatableCuisineOrigin = cuisineOriginRepository.findById(cuisineOrigin.getId())
                .orElseThrow(
                    () -> new ItemNotFoundException(ErrorMessage.CUISINE_ORIGIN_NOT_FOUND + cuisineOrigin.getId()));
        }
        if (isNotBlank(cuisineOrigin.getDescription())) {
            updatableCuisineOrigin.setDescription(cuisineOrigin.getDescription());
            updatableCuisineOrigin = cuisineOriginRepository.save(cuisineOrigin);
        } else {
            throw new IllegalArgumentException(ErrorMessage.ALL_FIELDS_MUST_BE_FILLED_FOR_NEW_ENTITY + cuisineOrigin);
        }
        return updatableCuisineOrigin;
    }

    @Override
    public MainCourseDto addOrUpdateMainCourse(MainCourseDto mainCourseDto) {
        MainCourse mainCourse;
        if (!isIdValid(mainCourseDto.getId()) && isNewMainCourseValid(mainCourseDto)) {
            CuisineOrigin cuisineOrigin = getCuisineOriginByDescription(mainCourseDto.getCuisineOrigin());
            mainCourse = mainCourseRepository.save(MainCourse.builder()
                .name(mainCourseDto.getName())
                .price(mainCourseDto.getPrice())
                .cuisine(cuisineOrigin)
                .build());
            return modelMapper.map(mainCourse, MainCourseDto.class);
        }

        if (isIdValid(mainCourseDto.getId())) {
            mainCourse = mainCourseRepository.findById(mainCourseDto.getId())
                .orElseThrow(
                    () -> new ItemNotFoundException(ErrorMessage.MAIN_COURSE_NOT_FOUND + mainCourseDto.getId()));
        } else {
            throw new IllegalArgumentException(ErrorMessage.ALL_FIELDS_MUST_BE_FILLED_FOR_NEW_ENTITY + mainCourseDto);
        }

        if (isNotBlank(mainCourseDto.getName())) {
            mainCourse.setName(mainCourseDto.getName());
        }
        if (nonNull(mainCourseDto.getPrice()) && mainCourseDto.getPrice() >= 0) {
            mainCourse.setPrice(mainCourseDto.getPrice());
        }
        if (isNotBlank(mainCourseDto.getCuisineOrigin())) {
            CuisineOrigin cuisineOrigin = getCuisineOriginByDescription(mainCourseDto.getCuisineOrigin());
            mainCourse.setCuisine(cuisineOrigin);
        }
        return modelMapper.map(mainCourse, MainCourseDto.class);
    }

    @Override
    public DessertDto addOrUpdateDessert(DessertDto dessertDto) {
        Dessert dessert;
        if (!isIdValid(dessertDto.getId()) && isNewDessertValid(dessertDto)) {
            CuisineOrigin cuisineOrigin = getCuisineOriginByDescription(dessertDto.getCuisineOrigin());
            dessert = dessertRepository.save(Dessert.builder()
                .name(dessertDto.getName())
                .price(dessertDto.getPrice())
                .cuisine(cuisineOrigin)
                .build());
            return modelMapper.map(dessert, DessertDto.class);
        }
        if (isIdValid(dessertDto.getId())) {
            dessert = dessertRepository.findById(dessertDto.getId())
                .orElseThrow(() -> new ItemNotFoundException(ErrorMessage.DESSERT_NOT_FOUND + dessertDto.getId()));
        } else {
            throw new IllegalArgumentException(ErrorMessage.ALL_FIELDS_MUST_BE_FILLED_FOR_NEW_ENTITY + dessertDto);
        }
        if (isNotBlank(dessertDto.getName())) {
            dessert.setName(dessertDto.getName());
        }
        if (nonNull(dessertDto.getPrice()) && dessertDto.getPrice() >= 0) {
            dessert.setPrice(dessertDto.getPrice());
        }
        if (isNotBlank(dessertDto.getCuisineOrigin())) {
            CuisineOrigin cuisineOrigin = getCuisineOriginByDescription(dessertDto.getCuisineOrigin());
            dessert.setCuisine(cuisineOrigin);
        }
        return modelMapper.map(dessert, DessertDto.class);
    }

    @Override
    public DrinkDto addOrUpdateDrink(DrinkDto drinkDto) {
        Drink drink;
        if (!isIdValid(drinkDto.getId()) && isNewDrinkValid(drinkDto)) {
            drink = drinkRepository.save(Drink.builder()
                .name(drinkDto.getName())
                .price(drinkDto.getPrice())
                .build());
            return modelMapper.map(drink, DrinkDto.class);
        }

        if (isIdValid(drinkDto.getId())) {
            drink = drinkRepository.findById(drinkDto.getId())
                .orElseThrow(() -> new ItemNotFoundException(ErrorMessage.DRINK_NOT_FOUND + drinkDto.getId()));
        } else {
            throw new IllegalArgumentException(ErrorMessage.ALL_FIELDS_MUST_BE_FILLED_FOR_NEW_ENTITY + drinkDto);
        }

        if (isNotBlank(drinkDto.getName())) {
            drink.setName(drinkDto.getName());
        }
        if (nonNull(drinkDto.getPrice()) && drinkDto.getPrice() >= 0) {
            drink.setPrice(drinkDto.getPrice());
        }

        drink = drinkRepository.save(drink);
        return modelMapper.map(drink, DrinkDto.class);
    }

    private boolean isNewMainCourseValid(MainCourseDto mainCourseDto) {
        return mainCourseDto != null
            && mainCourseDto.getPrice() != null && mainCourseDto.getPrice() > 0
            && isNotBlank(mainCourseDto.getName())
            && isNotBlank(mainCourseDto.getCuisineOrigin());
    }

    private boolean isNewDessertValid(DessertDto dessertDto) {
        return nonNull(dessertDto)
            && nonNull(dessertDto.getPrice()) && dessertDto.getPrice() > 0
            && isNotBlank(dessertDto.getName())
            && isNotBlank(dessertDto.getCuisineOrigin());
    }

    private boolean isNewDrinkValid(DrinkDto drinkDto) {
        return nonNull(drinkDto)
            && nonNull(drinkDto.getPrice()) && drinkDto.getPrice() > 0
            && isNotBlank(drinkDto.getName());
    }

    private CuisineOrigin getCuisineOriginByDescription(String description) {
        return cuisineOriginRepository.findByDescription(description)
            .orElseThrow(() -> new ItemNotFoundException(
                ErrorMessage.CUISINE_ORIGIN_NOT_FOUND_BY_DESCRIPTION + description));
    }

    private boolean isIdValid(Long id) {
        return id != null && id > 0;
    }
}
