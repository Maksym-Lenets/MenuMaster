package com.menumaster.service;

import com.menumaster.ModelUtils;
import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.entity.menu.Dessert;
import com.menumaster.entity.menu.Drink;
import com.menumaster.entity.menu.MainCourse;
import com.menumaster.repository.DessertRepository;
import com.menumaster.repository.DrinkRepository;
import com.menumaster.repository.MainCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {
    @InjectMocks
    private MenuServiceImpl menuService;

    @Mock
    private MainCourseRepository mainCourseRepository;
    @Mock
    private DessertRepository dessertRepository;
    @Mock
    private DrinkRepository drinkRepository;
    @Mock
    private ModelMapper modelMapper;
    List<MainCourse> mainCourses;
    List<Dessert> desserts;
    List<Drink> drinks;
    List<MainCourseDto> mainCourseDtos;
    List<DessertDto> dessertDtos;
    List<DrinkDto> drinkDtos;

    @BeforeEach
    void init() {
        mainCourses = ModelUtils.getMainCourses();
        desserts = ModelUtils.getDesserts();
        drinks = ModelUtils.getDrinks();
        mainCourseDtos = ModelUtils.getMainCourseDtos();
        dessertDtos = ModelUtils.getDessertDtos();
        drinkDtos = ModelUtils.getDrinkDtos();
    }

    @Test
    void getMenuTest() {
        when(mainCourseRepository.findAll()).thenReturn(mainCourses);
        when(dessertRepository.findAll()).thenReturn(desserts);
        when(drinkRepository.findAll()).thenReturn(drinks);

        when(modelMapper.map(mainCourses.get(0), MainCourseDto.class)).thenReturn(mainCourseDtos.get(0));
        when(modelMapper.map(mainCourses.get(1), MainCourseDto.class)).thenReturn(mainCourseDtos.get(1));
        when(modelMapper.map(mainCourses.get(2), MainCourseDto.class)).thenReturn(mainCourseDtos.get(2));

        when(modelMapper.map(desserts.get(0), DessertDto.class)).thenReturn(dessertDtos.get(0));
        when(modelMapper.map(desserts.get(1), DessertDto.class)).thenReturn(dessertDtos.get(1));
        when(modelMapper.map(desserts.get(2), DessertDto.class)).thenReturn(dessertDtos.get(2));

        when(modelMapper.map(drinks.get(0), DrinkDto.class)).thenReturn(drinkDtos.get(0));
        when(modelMapper.map(drinks.get(1), DrinkDto.class)).thenReturn(drinkDtos.get(1));
        when(modelMapper.map(drinks.get(2), DrinkDto.class)).thenReturn(drinkDtos.get(2));

        var menuDto = menuService.getMenu();

        verify(mainCourseRepository).findAll();
        verify(dessertRepository).findAll();
        verify(drinkRepository).findAll();

        assertEquals(mainCourseDtos, menuDto.getMainCourseList());
        assertEquals(dessertDtos, menuDto.getDesserts());
        assertEquals(drinkDtos, menuDto.getDrinks());
    }

    @Test
    void getMainCoursesTest() {
        when(mainCourseRepository.findAll()).thenReturn(mainCourses);

        when(modelMapper.map(mainCourses.get(0), MainCourseDto.class)).thenReturn(mainCourseDtos.get(0));
        when(modelMapper.map(mainCourses.get(1), MainCourseDto.class)).thenReturn(mainCourseDtos.get(1));
        when(modelMapper.map(mainCourses.get(2), MainCourseDto.class)).thenReturn(mainCourseDtos.get(2));

        var result = menuService.getMainCourses();

        verify(mainCourseRepository).findAll();
        assertEquals(mainCourseDtos, result);
    }

    @Test
    void getDessertsTest() {
        when(dessertRepository.findAll()).thenReturn(desserts);

        when(modelMapper.map(desserts.get(0), DessertDto.class)).thenReturn(dessertDtos.get(0));
        when(modelMapper.map(desserts.get(1), DessertDto.class)).thenReturn(dessertDtos.get(1));
        when(modelMapper.map(desserts.get(2), DessertDto.class)).thenReturn(dessertDtos.get(2));

        var result = menuService.getDesserts();

        verify(dessertRepository).findAll();
        assertEquals(dessertDtos, result);
    }

    @Test
    void getDrinksTest() {
        when(drinkRepository.findAll()).thenReturn(drinks);

        when(modelMapper.map(drinks.get(0), DrinkDto.class)).thenReturn(drinkDtos.get(0));
        when(modelMapper.map(drinks.get(1), DrinkDto.class)).thenReturn(drinkDtos.get(1));
        when(modelMapper.map(drinks.get(2), DrinkDto.class)).thenReturn(drinkDtos.get(2));

        var result = menuService.getDrinks();

        verify(drinkRepository).findAll();
        assertEquals(drinkDtos, result);
    }

}