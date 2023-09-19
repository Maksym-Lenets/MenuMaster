package com.menumaster.service;

import com.menumaster.ModelUtils;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @InjectMocks
    private AdminServiceImpl adminService;
    @Mock
    private CuisineOriginRepository cuisineOriginRepository;
    @Mock
    private MainCourseRepository mainCourseRepository;
    @Mock
    private DessertRepository dessertRepository;
    @Mock
    private DrinkRepository drinkRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void addOrUpdateCuisineOriginNewCuisineOriginTest() {
        CuisineOrigin newCuisineOrigin = ModelUtils.getNewCuisineOrigin();
        CuisineOrigin savedCuisineOrigin = ModelUtils.getSavedNewCuisineOrigin();
        when(cuisineOriginRepository.save(any())).thenReturn(savedCuisineOrigin);

        CuisineOrigin result = adminService.addOrUpdateCuisineOrigin(newCuisineOrigin);

        verify(cuisineOriginRepository, never()).findById(any());
        verify(cuisineOriginRepository).save(newCuisineOrigin);
        assertEquals(result, savedCuisineOrigin);
    }

    @Test
    void addOrUpdateCuisineOriginUpdateCuisineOriginTest() {
        CuisineOrigin updatedNewCuisineOrigin = ModelUtils.getUpdatedCuisineOrigin();
        CuisineOrigin savedCuisineOrigin = ModelUtils.getSavedNewCuisineOrigin();
        when(cuisineOriginRepository.findById(updatedNewCuisineOrigin.getId()))
            .thenReturn(Optional.ofNullable(savedCuisineOrigin));

        CuisineOrigin result = adminService.addOrUpdateCuisineOrigin(updatedNewCuisineOrigin);

        verify(cuisineOriginRepository).findById(any());
        verify(cuisineOriginRepository, never()).save(any());
        assertEquals(result, updatedNewCuisineOrigin);
    }

    @Test
    void addOrUpdateCuisineOriginUpdateCuisineOriginWithNotExistingIdTest() {
        CuisineOrigin newCuisineOrigin = ModelUtils.getUpdatedCuisineOrigin();
        when(cuisineOriginRepository.findById(newCuisineOrigin.getId())).thenReturn(Optional.empty());

        ItemNotFoundException itemNotFoundException =
            assertThrows(ItemNotFoundException.class,
                () -> adminService.addOrUpdateCuisineOrigin(newCuisineOrigin));

        verify(cuisineOriginRepository, never()).save(any());
        assertEquals(ErrorMessage.CUISINE_ORIGIN_NOT_FOUND + newCuisineOrigin.getId(),
            itemNotFoundException.getMessage());
    }

    @Test
    void addOrUpdateMainCourseNewMainCourseTest() {
        MainCourseDto newMainCourseDto = ModelUtils.getNewMainCourseDto();
        MainCourseDto updatedMainCourseDto = ModelUtils.getUpdatedMainCourseDto();
        MainCourse updatedMainCourse = ModelUtils.getUpdatedMainCourse();

        when(modelMapper.map(updatedMainCourse, MainCourseDto.class)).thenReturn(updatedMainCourseDto);
        when(cuisineOriginRepository.findByDescription(newMainCourseDto.getCuisineOrigin()))
            .thenReturn(Optional.of(ModelUtils.getUpdatedCuisineOrigin()));
        when(mainCourseRepository.save(any())).thenReturn(updatedMainCourse);

        MainCourseDto result = adminService.addOrUpdateMainCourse(newMainCourseDto);

        verify(mainCourseRepository, never()).findById(any());
        verify(mainCourseRepository).save(any());
        verify(cuisineOriginRepository).findByDescription(newMainCourseDto.getCuisineOrigin());
        assertEquals(updatedMainCourseDto, result);
    }

    @Test
    void addOrUpdateMainCourseUpdatedMainCourseTest() {
        MainCourseDto updatedMainCourseDto = ModelUtils.getUpdatedMainCourseDto();
        MainCourse savedMainCourse = ModelUtils.getSavedMainCourse();
        MainCourse updatedMainCourse = ModelUtils.getUpdatedMainCourse();

        when(mainCourseRepository.findById(updatedMainCourseDto.getId())).thenReturn(Optional.of(savedMainCourse));
        when(modelMapper.map(updatedMainCourse, MainCourseDto.class)).thenReturn(updatedMainCourseDto);
        when(cuisineOriginRepository.findByDescription(updatedMainCourseDto.getCuisineOrigin()))
            .thenReturn(Optional.of(ModelUtils.getUpdatedCuisineOrigin()));
        when(mainCourseRepository.save(any())).thenReturn(updatedMainCourse);

        MainCourseDto result = adminService.addOrUpdateMainCourse(updatedMainCourseDto);

        verify(mainCourseRepository).findById(updatedMainCourseDto.getId());
        verify(mainCourseRepository).save(updatedMainCourse);
        verify(cuisineOriginRepository).findByDescription(updatedMainCourseDto.getCuisineOrigin());
        assertEquals(updatedMainCourseDto, result);
    }

    @Test
    void addOrUpdateMainCourseUpdatedMainCourseWithNotExistingIdTest() {
        MainCourseDto updatedMainCourseDto = ModelUtils.getUpdatedMainCourseDto();

        when(mainCourseRepository.findById(updatedMainCourseDto.getId())).thenReturn(Optional.empty());

        ItemNotFoundException itemNotFoundException =
            assertThrows(ItemNotFoundException.class,
                () -> adminService.addOrUpdateMainCourse(updatedMainCourseDto));

        verify(mainCourseRepository).findById(updatedMainCourseDto.getId());
        verify(mainCourseRepository, never()).save(any());
        verify(cuisineOriginRepository, never()).findByDescription(updatedMainCourseDto.getCuisineOrigin());
        assertEquals(ErrorMessage.MAIN_COURSE_NOT_FOUND + updatedMainCourseDto.getId(),
            itemNotFoundException.getMessage());
    }

    @Test
    void addOrUpdateDessertNewDessertTest() {
        DessertDto newDessertDto = ModelUtils.getNewDessertDto();
        DessertDto savedDessertDto = ModelUtils.getUpdatedDessertDto();
        Dessert updatedDessert = ModelUtils.getUpdatedDessert();

        when(modelMapper.map(updatedDessert, DessertDto.class)).thenReturn(savedDessertDto);
        when(cuisineOriginRepository.findByDescription(newDessertDto.getCuisineDescription()))
            .thenReturn(Optional.of(ModelUtils.getUpdatedCuisineOrigin()));
        when(dessertRepository.save(any())).thenReturn(updatedDessert);

        DessertDto result = adminService.addOrUpdateDessert(newDessertDto);

        verify(dessertRepository, never()).findById(any());
        verify(dessertRepository).save(any());
        verify(cuisineOriginRepository).findByDescription(newDessertDto.getCuisineDescription());
        assertEquals(savedDessertDto, result);
    }

    @Test
    void addOrUpdateDessertUpdatedDessertTest() {
        DessertDto updatedDessertDto = ModelUtils.getUpdatedDessertDto();
        Dessert savedDessert = ModelUtils.getSavedDessert();
        Dessert updatedDessert = ModelUtils.getUpdatedDessert();

        when(dessertRepository.findById(updatedDessertDto.getId())).thenReturn(Optional.of(savedDessert));
        when(modelMapper.map(updatedDessert, DessertDto.class)).thenReturn(updatedDessertDto);
        when(cuisineOriginRepository.findByDescription(updatedDessertDto.getCuisineDescription()))
            .thenReturn(Optional.of(ModelUtils.getUpdatedCuisineOrigin()));
        when(dessertRepository.save(any())).thenReturn(updatedDessert);

        DessertDto result = adminService.addOrUpdateDessert(updatedDessertDto);

        verify(dessertRepository).findById(updatedDessertDto.getId());
        verify(dessertRepository).save(updatedDessert);
        verify(cuisineOriginRepository).findByDescription(updatedDessertDto.getCuisineDescription());
        assertEquals(updatedDessertDto, result);
    }

    @Test
    void addOrUpdateDessertUpdatedDessertWithNotExistingIdTest() {
        DessertDto updatedDessertDto = ModelUtils.getUpdatedDessertDto();

        when(dessertRepository.findById(updatedDessertDto.getId())).thenReturn(Optional.empty());

        ItemNotFoundException itemNotFoundException =
            assertThrows(ItemNotFoundException.class,
                () -> adminService.addOrUpdateDessert(updatedDessertDto));

        verify(dessertRepository).findById(updatedDessertDto.getId());
        verify(dessertRepository, never()).save(any());
        verify(cuisineOriginRepository, never()).findByDescription(updatedDessertDto.getCuisineDescription());
        assertEquals(ErrorMessage.DESSERT_NOT_FOUND + updatedDessertDto.getId(), itemNotFoundException.getMessage());
    }

    @Test
    void addOrUpdateDrinkNewDrinkTest() {
        DrinkDto newDrinkDto = ModelUtils.getNewDrinkDto();
        DrinkDto updatedDrinkDto = ModelUtils.getUpdatedDrinkDto();
        Drink updatedDrink = ModelUtils.getUpdatedDrink();

        when(modelMapper.map(updatedDrink, DrinkDto.class)).thenReturn(updatedDrinkDto);
        when(drinkRepository.save(any())).thenReturn(updatedDrink);

        DrinkDto result = adminService.addOrUpdateDrink(newDrinkDto);

        verify(drinkRepository, never()).findById(any());
        verify(drinkRepository).save(any());
        assertEquals(updatedDrinkDto, result);
    }

    @Test
    void addOrUpdateDrinkUpdatedDrinkTest() {
        DrinkDto updatedDrinkDto = ModelUtils.getUpdatedDrinkDto();
        Drink savedDrink = ModelUtils.getSavedDrink();
        Drink updatedDrink = ModelUtils.getUpdatedDrink();

        when(drinkRepository.findById(updatedDrinkDto.getId())).thenReturn(Optional.of(savedDrink));
        when(modelMapper.map(updatedDrink, DrinkDto.class)).thenReturn(updatedDrinkDto);
        when(drinkRepository.save(any())).thenReturn(updatedDrink);

        DrinkDto result = adminService.addOrUpdateDrink(updatedDrinkDto);

        verify(drinkRepository).findById(updatedDrinkDto.getId());
        verify(drinkRepository).save(updatedDrink);
        assertEquals(updatedDrinkDto, result);
    }

    @Test
    void addOrUpdateDrinkUpdatedDrinkWithNotExistingIdTest() {
        DrinkDto updatedDrinkDto = ModelUtils.getUpdatedDrinkDto();

        when(drinkRepository.findById(updatedDrinkDto.getId())).thenReturn(Optional.empty());

        ItemNotFoundException itemNotFoundException = assertThrows(
            ItemNotFoundException.class,
            () -> adminService.addOrUpdateDrink(updatedDrinkDto));

        verify(drinkRepository).findById(updatedDrinkDto.getId());
        verify(drinkRepository, never()).save(any());
        assertEquals(ErrorMessage.DRINK_NOT_FOUND + updatedDrinkDto.getId(), itemNotFoundException.getMessage());
    }
}
