package com.menumaster.service;

import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.dto.MenuDto;
import com.menumaster.repository.DessertRepository;
import com.menumaster.repository.DrinkRepository;
import com.menumaster.repository.MainCourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    private final MainCourseRepository mainCourseRepository;
    private final DessertRepository dessertRepository;
    private final DrinkRepository drinkRepository;
    private final ModelMapper modelMapper;

    @Override
    public MenuDto getMenu() {
        return MenuDto.builder()
            .mainCourseList(getMainCourses())
            .desserts(getDesserts())
            .drinks(getDrinks())
            .build();
    }

    @Override
    public List<MainCourseDto> getMainCourses() {
        return mainCourseRepository.findAll().stream()
            .map(mc -> modelMapper.map(mc, MainCourseDto.class))
            .toList();
    }

    @Override
    public List<DessertDto> getDesserts() {
        var test = dessertRepository.findAll();
        return test.stream()
            .map(d -> modelMapper.map(d, DessertDto.class))
            .toList();
    }

    @Override
    public List<DrinkDto> getDrinks() {
        return drinkRepository.findAll().stream()
            .map(d -> modelMapper.map(d, DrinkDto.class))
            .toList();
    }
}
