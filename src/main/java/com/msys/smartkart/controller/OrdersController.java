package com.msys.smartkart.controller;

import com.msys.smartkart.config.GenericResponseDto;
import com.msys.smartkart.entity.Orders;
import com.msys.smartkart.service.OrdersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class OrdersController {
    final OrdersService ordersService;

    @GetMapping("/buyNow")
    public ResponseEntity<GenericResponseDto> buyNow(@RequestParam("userId") final Integer userId,
                                                     @RequestParam("productId") final Integer id) {
        log.info(String.valueOf(userId));
        log.info(String.valueOf(id));
        final Orders orders = ordersService.placeOrder(userId, id);

        log.info("Order Placed successfully");
        return new ResponseEntity<>(new GenericResponseDto(200, orders), HttpStatus.ACCEPTED);
    }


    public String orderCart(@RequestParam("userId") final Integer userId){

        ordersService.orderCart(userId);
        return "Cart Ordered successfully";
    }

    @GetMapping("/fetchOrders")
    public ResponseEntity<GenericResponseDto> ordersHistory(@RequestParam("userId") final Integer userId) {
        log.info("Finding list of orders of user Id : " + userId);
        final List<Orders> orders = ordersService.fetchOrders(userId);
        return new ResponseEntity<>(new GenericResponseDto(200, orders), HttpStatus.ACCEPTED);
    }
}
