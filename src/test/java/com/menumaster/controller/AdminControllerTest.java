package com.menumaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menumaster.ModelUtils;
import com.menumaster.dto.DessertDto;
import com.menumaster.dto.DrinkDto;
import com.menumaster.dto.MainCourseDto;
import com.menumaster.entity.menu.CuisineOrigin;
import com.menumaster.exception.ItemNotFoundException;
import com.menumaster.service.AdminService;
import org.assertj.core.api.Assertions;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    @InjectMocks
    private AdminController adminController;
    @Mock
    private AdminService adminService;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void addOrUpdateCuisineOriginTest() throws Exception {
        CuisineOrigin newCuisineOrigin = ModelUtils.getNewCuisineOrigin();
        String jsonRequest = objectMapper.writeValueAsString(newCuisineOrigin);
        CuisineOrigin savedCuisineOrigin = ModelUtils.getUpdatedCuisineOrigin();
        String jsonResult = objectMapper.writeValueAsString(savedCuisineOrigin);

        when(adminService.addOrUpdateCuisineOrigin(newCuisineOrigin)).thenReturn(savedCuisineOrigin);

        mockMvc.perform(post("/admin/addCuisineOrigin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResult));

        verify(adminService).addOrUpdateCuisineOrigin(any());
    }

    @Test
    void addOrUpdateCuisineOriginNotFoundExceptionTest() throws Exception {
        CuisineOrigin cuisineOrigin = ModelUtils.getNewCuisineOrigin();
        String jsonRequest = objectMapper.writeValueAsString(cuisineOrigin);
        String exceptionMessage = "Some exception";

        doThrow(new ItemNotFoundException(exceptionMessage))
            .when(adminService)
            .addOrUpdateCuisineOrigin(cuisineOrigin);
        Assertions.assertThatThrownBy(() -> mockMvc.perform(post("/admin/addCuisineOrigin")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isNotFound()))
            .hasCause(new ItemNotFoundException(exceptionMessage));

        verify(adminService).addOrUpdateCuisineOrigin(cuisineOrigin);
    }

    @Test
    void addOrUpdateMainCourseTest() throws Exception {
        MainCourseDto mainCourseDto = ModelUtils.getNewMainCourseDto();
        String jsonRequest = objectMapper.writeValueAsString(mainCourseDto);
        MainCourseDto savedMainCourseDto = ModelUtils.getUpdatedMainCourseDto();
        String jsonResult = objectMapper.writeValueAsString(savedMainCourseDto);

        when(adminService.addOrUpdateMainCourse(mainCourseDto)).thenReturn(savedMainCourseDto);

        mockMvc.perform(post("/admin/addMainCourse")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResult));

        verify(adminService).addOrUpdateMainCourse(mainCourseDto);
    }

    @Test
    void addOrUpdateMainCourseNotFoundExceptionTest() throws Exception {
        MainCourseDto mainCourseDto = ModelUtils.getUpdatedMainCourseDto();
        String jsonRequest = objectMapper.writeValueAsString(mainCourseDto);
        String exceptionMessage = "Some exception";

        doThrow(new ItemNotFoundException(exceptionMessage))
            .when(adminService)
            .addOrUpdateMainCourse(mainCourseDto);
        Assertions.assertThatThrownBy(() -> mockMvc.perform(post("/admin/addMainCourse")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isNotFound()))
            .hasCause(new ItemNotFoundException(exceptionMessage));

        verify(adminService).addOrUpdateMainCourse(mainCourseDto);
    }

    @Test
    void addOrUpdateDessertTest() throws Exception {
        DessertDto dessertDto = ModelUtils.getNewDessertDto();
        String jsonRequest = objectMapper.writeValueAsString(dessertDto);
        DessertDto savedDessertDto = ModelUtils.getUpdatedDessertDto();
        String jsonResponse = objectMapper.writeValueAsString(savedDessertDto);

        when(adminService.addOrUpdateDessert(dessertDto)).thenReturn(savedDessertDto);

        mockMvc.perform(post("/admin/addDessert")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResponse));

        verify(adminService).addOrUpdateDessert(dessertDto);
    }

    @Test
    void addOrUpdateDessertNotFoundExceptionTest() throws Exception {
        DessertDto newDessertDto = ModelUtils.getNewDessertDto();
        String jsonRequest = objectMapper.writeValueAsString(newDessertDto);
        String exceptionMessage = "Some exception";

        doThrow(new ItemNotFoundException(exceptionMessage))
            .when(adminService)
            .addOrUpdateDessert(newDessertDto);

        Assertions.assertThatThrownBy(() -> mockMvc.perform(post("/admin/addDessert")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isNotFound()))
            .hasCause(new ItemNotFoundException(exceptionMessage));

        verify(adminService).addOrUpdateDessert(newDessertDto);
    }

    @Test
    void addOrUpdateDrinkTest() throws Exception {
        DrinkDto drinkDto = ModelUtils.getNewDrinkDto();
        String jsonRequest = objectMapper.writeValueAsString(drinkDto);
        DrinkDto savedDrinkDto = ModelUtils.getUpdatedDrinkDto();
        String jsonResponse = objectMapper.writeValueAsString(savedDrinkDto);

        when(adminService.addOrUpdateDrink(drinkDto)).thenReturn(savedDrinkDto);

        mockMvc.perform(post("/admin/addDrink")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(jsonResponse));

        verify(adminService).addOrUpdateDrink(drinkDto);
    }

    @Test
    void addOrUpdateDrinkNotFoundExceptionTest() throws Exception {
        DrinkDto drinkDto = ModelUtils.getNewDrinkDto();
        String jsonRequest = objectMapper.writeValueAsString(drinkDto);
        String exceptionMessage = "Some exception";

        doThrow(new ItemNotFoundException(exceptionMessage))
            .when(adminService)
            .addOrUpdateDrink(drinkDto);

        Assertions.assertThatThrownBy(() -> mockMvc.perform(post("/admin/addDrink")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
            .andExpect(status().isNotFound()))
            .hasCause(new ItemNotFoundException(exceptionMessage));

        verify(adminService).addOrUpdateDrink(drinkDto);

    }
}