package com.springtraining.order.controller;


import com.springtraining.order.dto.RestaurantOrderDto;
import com.springtraining.order.entity.RestaurantOrder;
import com.springtraining.order.repository.RestaurantOrderRepository;
import com.springtraining.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantorder")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/getOrder")
    public ResponseEntity<RestaurantOrderDto> getOrder
            (@RequestParam(value="consumerId") String consumerId) {

        log.info("Calling order service for consumer id : {}",consumerId);
        RestaurantOrderDto restaurantOrderDto = orderService.getOrder(consumerId);
        log.info("received response :");

        ResponseEntity<RestaurantOrderDto> responseEntity = new ResponseEntity<>
                (restaurantOrderDto, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<RestaurantOrderDto> createRestaurantOrder
            (@RequestBody RestaurantOrderDto restaurantOrderDto){

        log.info("New order request received");
        RestaurantOrderDto restaurantSavedOrderDto =
                orderService.createOrder(restaurantOrderDto);
        log.info("New order created");

        ResponseEntity<RestaurantOrderDto> responseEntity;
        if(restaurantSavedOrderDto.getFlag()){
             responseEntity = new ResponseEntity<>
                    (restaurantOrderDto, HttpStatus.OK);
        }else{

            responseEntity = new ResponseEntity<>
                    (restaurantOrderDto, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}
