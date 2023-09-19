package com.menumaster.controller;

import com.menumaster.dto.CreatedOrderDto;
import com.menumaster.dto.NewOrderDto;
import com.menumaster.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Order API for customer")
public class OrderController {
    private final OrderService orderService;

    /**
     * Create a new order. This endpoint allows customers to create a new order by
     * providing the order details in the request body. Upon successful creation, it
     * returns the saved order along with the total price.
     *
     * @param order The OrderNewDto containing the order details.
     * @return A ResponseEntity containing the saved order with a status code of 201
     *         (Created).
     */
    @Operation(
        summary = "Make order",
        description = "returns saved order with total price")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "order successfully saved"),
        @ApiResponse(responseCode = "404", description = "Invalid MenuItem id")
    })
    @PostMapping
    public ResponseEntity<CreatedOrderDto> createOrder(@RequestBody @Valid NewOrderDto order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addNewOrder(order));
    }
}
