package com.menumaster.service;

import com.menumaster.constatnt.ErrorMessage;
import com.menumaster.dto.CreatedOrderDto;
import com.menumaster.dto.LunchDto;
import com.menumaster.dto.NewOrderDto;
import com.menumaster.dto.OrderedDrinkDto;
import com.menumaster.entity.Lunch;
import com.menumaster.entity.Order;
import com.menumaster.entity.OrderedDrink;
import com.menumaster.entity.menu.Dessert;
import com.menumaster.entity.menu.Drink;
import com.menumaster.entity.menu.MainCourse;
import com.menumaster.exception.ItemNotFoundException;
import com.menumaster.repository.DessertRepository;
import com.menumaster.repository.DrinkRepository;
import com.menumaster.repository.MainCourseRepository;
import com.menumaster.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DrinkRepository drinkRepository;
    private final DessertRepository dessertRepository;
    private final MainCourseRepository mainCourseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CreatedOrderDto addNewOrder(NewOrderDto orderDto) {
        Order order = new Order();
        if (orderDto.getDrinks() != null && !orderDto.getDrinks().isEmpty()) {
            order.addOrderedDrinks(createDrinkForOrder(orderDto.getDrinks()));
        }
        if (orderDto.getLunches() != null && !orderDto.getLunches().isEmpty()) {
            order.setLunches(createLunchForOrder(orderDto.getLunches()));
        }
        order = orderRepository.save(order);

        CreatedOrderDto createdOrderDto = modelMapper.map(order, CreatedOrderDto.class);
        createdOrderDto.setDrinks(order.getDrinks().stream()
            .map(d -> modelMapper.map(d, OrderedDrinkDto.class))
            .toList());
        createdOrderDto.setLunches(order.getLunches().stream()
            .map(l -> modelMapper.map(l, LunchDto.class))
            .toList());
        return createdOrderDto;
    }

    private List<Lunch> createLunchForOrder(List<LunchDto> orderedLunches) {
        var orderedMainCoursesIds = new HashSet<Long>();
        var orderedDessertsIds = new HashSet<Long>();
        for (var lunch : orderedLunches) {
            orderedMainCoursesIds.add(lunch.getMainCourseId());
            orderedDessertsIds.add(lunch.getDessertId());
        }
        var desserts = dessertRepository.findAllById(orderedDessertsIds).stream()
            .collect(Collectors.toMap(Dessert::getId, d -> d));
        var mainCourses = mainCourseRepository.findAllById(orderedMainCoursesIds).stream()
            .collect(Collectors.toMap(MainCourse::getId, mc -> mc));

        if (orderedMainCoursesIds.size() != mainCourses.size()) {
            var notFoundedDrinksIds = findMissingIds(mainCourses.keySet(), orderedMainCoursesIds);
            String notExistingIds = notFoundedDrinksIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            throw new ItemNotFoundException(ErrorMessage.MAIN_COURSE_NOT_FOUND + notExistingIds);
        }

        if (orderedDessertsIds.size() != desserts.size()) {
            var notFoundedDrinksIds = findMissingIds(desserts.keySet(), orderedDessertsIds);
            String notExistingIds = notFoundedDrinksIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            throw new ItemNotFoundException(ErrorMessage.DESSERT_NOT_FOUND + notExistingIds);
        }
        List<Lunch> lunches = new ArrayList<>();

        for (var lunchDto : orderedLunches) {
            lunches.add(Lunch.builder()
                .mainCourse(mainCourses.get(lunchDto.getMainCourseId()))
                .dessert(desserts.get(lunchDto.getDessertId()))
                .build());
        }

        return lunches;
    }

    private List<OrderedDrink> createDrinkForOrder(List<OrderedDrinkDto> orderedDrinks) {
        var orderedDrinksIds = orderedDrinks.stream()
            .map(OrderedDrinkDto::getDrinkId)
            .collect(Collectors.toSet());
        var drinks = drinkRepository.findAllById(orderedDrinksIds).stream()
            .collect(Collectors.toMap(Drink::getId, d -> d));

        if (orderedDrinksIds.size() != drinks.size()) {
            var notFoundedDrinksIds = findMissingIds(drinks.keySet(), orderedDrinksIds);
            String notExistingIds = notFoundedDrinksIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            throw new ItemNotFoundException(ErrorMessage.DRINK_NOT_FOUND + notExistingIds);
        }

        return orderedDrinks.stream()
            .map(dto -> OrderedDrink.builder()
                .iceCubes(dto.isIceCubes())
                .lemons(dto.isLemons())
                .drink(drinks.get(dto.getDrinkId()))
                .build())
            .toList();
    }

    private Set<Long> findMissingIds(Set<Long> source, Set<Long> searched) {
        return searched.stream()
            .filter(id -> !source.contains(id))
            .collect(Collectors.toSet());
    }
}
