package com.menumaster.service;

import com.menumaster.ModelUtils;
import com.menumaster.constatnt.ErrorMessage;
import com.menumaster.dto.CreatedOrderDto;
import com.menumaster.dto.LunchDto;
import com.menumaster.dto.NewOrderDto;
import com.menumaster.dto.OrderedDrinkDto;
import com.menumaster.entity.Order;
import com.menumaster.entity.menu.Dessert;
import com.menumaster.entity.menu.Drink;
import com.menumaster.entity.menu.MainCourse;
import com.menumaster.exception.ItemNotFoundException;
import com.menumaster.repository.DessertRepository;
import com.menumaster.repository.DrinkRepository;
import com.menumaster.repository.MainCourseRepository;
import com.menumaster.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private DrinkRepository drinkRepository;
    @Mock
    private DessertRepository dessertRepository;
    @Mock
    private MainCourseRepository mainCourseRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    void addNewOrderTest() {
        NewOrderDto newOrderDto = ModelUtils.getOrderNewDto();
        Order order = ModelUtils.getNewOrder();
        CreatedOrderDto createdOrderDto = ModelUtils.getCreatedOrderDto();

        when(mainCourseRepository.findAllById(any())).thenReturn(ModelUtils.getSavedMainCourses());
        when(dessertRepository.findAllById(any())).thenReturn(ModelUtils.getSavedDesserts());
        when(drinkRepository.findAllById(any())).thenReturn(ModelUtils.getSavedDrinks());
        when(orderRepository.save(any())).thenReturn(order);

        when(modelMapper.map(order, CreatedOrderDto.class)).thenReturn(createdOrderDto);
        when(modelMapper.map(order.getLunches().get(0), LunchDto.class)).thenReturn(newOrderDto.getLunches().get(0));
        when(modelMapper.map(order.getLunches().get(1), LunchDto.class)).thenReturn(newOrderDto.getLunches().get(1));
        when(modelMapper.map(order.getDrinks().get(0), OrderedDrinkDto.class))
            .thenReturn(newOrderDto.getDrinks().get(0));
        when(modelMapper.map(order.getDrinks().get(1), OrderedDrinkDto.class))
            .thenReturn(newOrderDto.getDrinks().get(1));

        CreatedOrderDto result = orderService.addNewOrder(newOrderDto);

        verify(drinkRepository).findAllById(any());
        verify(dessertRepository).findAllById(any());
        verify(mainCourseRepository).findAllById(any());
        verify(orderRepository).save(any());

        assertEquals(createdOrderDto, result);
    }

    @Test
    void addNewOrderWithNotExistingMainCourseIdTest() {
        NewOrderDto newOrderDto = ModelUtils.getOrderNewDto();
        Long firstMainCourseId = newOrderDto.getLunches().get(0).getMainCourseId();
        Long secondMainCourseId = newOrderDto.getLunches().get(1).getMainCourseId();
        List<MainCourse> mainCourses = Collections.singletonList(MainCourse.builder().id(firstMainCourseId).build());

        when(mainCourseRepository.findAllById(any())).thenReturn(mainCourses);
        when(dessertRepository.findAllById(any())).thenReturn(ModelUtils.getSavedDesserts());
        when(drinkRepository.findAllById(any())).thenReturn(ModelUtils.getSavedDrinks());

        ItemNotFoundException exception =
            assertThrows(ItemNotFoundException.class, () -> orderService.addNewOrder(newOrderDto));

        verify(mainCourseRepository).findAllById(any());
        verify(orderRepository, never()).save(any());

        assertEquals(ErrorMessage.MAIN_COURSE_NOT_FOUND + secondMainCourseId, exception.getMessage());
    }

    @Test
    void addNewOrderWithNotExistingDessertIdTest() {
        NewOrderDto newOrderDto = ModelUtils.getOrderNewDto();
        Long firstDessertId = newOrderDto.getLunches().get(0).getDessertId();
        Long secondDessertId = newOrderDto.getLunches().get(1).getDessertId();
        List<Dessert> desserts = Collections.singletonList(Dessert.builder().id(firstDessertId).build());

        when(mainCourseRepository.findAllById(any())).thenReturn(ModelUtils.getSavedMainCourses());
        when(dessertRepository.findAllById(any())).thenReturn(desserts);
        when(drinkRepository.findAllById(any())).thenReturn(ModelUtils.getSavedDrinks());

        ItemNotFoundException exception =
            assertThrows(ItemNotFoundException.class, () -> orderService.addNewOrder(newOrderDto));

        verify(dessertRepository).findAllById(any());
        verify(orderRepository, never()).save(any());

        assertEquals(ErrorMessage.DESSERT_NOT_FOUND + secondDessertId, exception.getMessage());
    }

    @Test
    void addNewOrderWithNotExistingDrinkIdTest() {
        NewOrderDto newOrderDto = ModelUtils.getOrderNewDto();
        Long firstDrinkId = newOrderDto.getDrinks().get(0).getDrinkId();
        Long secondDrinkId = newOrderDto.getDrinks().get(1).getDrinkId();
        List<Drink> drinks = Collections.singletonList(Drink.builder().id(firstDrinkId).build());

        when(drinkRepository.findAllById(any())).thenReturn(drinks);

        ItemNotFoundException exception =
            assertThrows(ItemNotFoundException.class, () -> orderService.addNewOrder(newOrderDto));

        verify(drinkRepository).findAllById(any());
        verify(orderRepository, never()).save(any());

        assertEquals(ErrorMessage.DRINK_NOT_FOUND + secondDrinkId, exception.getMessage());

    }
}