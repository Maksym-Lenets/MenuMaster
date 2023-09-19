package com.menumaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menumaster.ModelUtils;
import com.menumaster.dto.CreatedOrderDto;
import com.menumaster.dto.NewOrderDto;
import com.menumaster.exception.ItemNotFoundException;
import com.menumaster.service.OrderService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .build();
    }

    @Test
    void createOrderTest() throws Exception {
        NewOrderDto newOrderDto = ModelUtils.getOrderNewDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String newOrderJson = objectMapper.writeValueAsString(newOrderDto);
        CreatedOrderDto createdOrderDto = ModelUtils.getCreatedOrderDto();
        String createdOrderJson = objectMapper.writeValueAsString(createdOrderDto);

        when(orderService.addNewOrder(newOrderDto)).thenReturn(ModelUtils.getCreatedOrderDto());

        this.mockMvc.perform(post("/order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newOrderJson))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(createdOrderJson));

        verify(orderService).addNewOrder(newOrderDto);
    }

    @Test
    void createOrderMenuItemNotFoundExceptionTest() throws Exception {
        NewOrderDto newOrderDto = ModelUtils.getOrderNewDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String newOrderJson = objectMapper.writeValueAsString(newOrderDto);
        String exceptionMessage = "Some text";

        doThrow(new ItemNotFoundException(exceptionMessage))
            .when(orderService)
            .addNewOrder(newOrderDto);

        Assertions.assertThatThrownBy(
            () -> mockMvc
                .perform(post("/order")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newOrderJson))
                .andExpect(status().isNotFound()))
            .hasCause(new ItemNotFoundException(exceptionMessage));
    }

}
