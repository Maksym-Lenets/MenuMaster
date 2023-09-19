package com.menumaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menumaster.ModelUtils;
import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.dto.MenuDto;
import com.menumaster.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {
    @InjectMocks
    private MenuController menuController;
    @Mock
    private MenuService menuService;

    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(menuController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getFullMenuTest() throws Exception {
        MenuDto result = ModelUtils.getMenuDto();
        String jsonResult = objectMapper.writeValueAsString(result);

        when(menuService.getMenu()).thenReturn(result);

        mockMvc.perform(get("/menu/allMenuItems"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResult));

        verify(menuService).getMenu();
    }

    @Test
    void getAllDrinksTest() throws Exception {
        List<DrinkDto> result = ModelUtils.getDrinkDtos();
        String jsonResult = objectMapper.writeValueAsString(result);

        when(menuService.getDrinks()).thenReturn(result);

        mockMvc.perform(get("/menu/drinks"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResult));

        verify(menuService).getDrinks();
    }

    @Test
    void getAllMainCoursesTest() throws Exception {
        List<MainCourseDto> result = ModelUtils.getMainCourseDtos();
        String jsonResult = objectMapper.writeValueAsString(result);

        when(menuService.getMainCourses()).thenReturn(result);

        mockMvc.perform(get("/menu/mainCourses"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResult));

        verify(menuService).getMainCourses();
    }

    @Test
    void getAllDessertsTest() throws Exception {
        List<DessertDto> result = ModelUtils.getDessertDtos();
        String jsonResult = objectMapper.writeValueAsString(result);

        when(menuService.getDesserts()).thenReturn(result);

        mockMvc.perform(get("/menu/desserts"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResult));

        verify(menuService).getDesserts();
    }

}