package com.springtraining.order.controller;


import com.springtraining.order.entity.RestaurantOrder;
import com.springtraining.order.repository.RestaurantOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantorder")
@Slf4j
public class OrderController {

    @Autowired
    RestaurantOrderRepository restaurantOrderRepository;

    @GetMapping("/getOrder")
    public RestaurantOrder  getOrder(@RequestParam(value="consumerId") String consumerId) {

        /*RestaurantOrder restaurantOrder = new RestaurantOrder();
        restaurantOrder.setCustomerId(consumerId);
        restaurantOrder.setOrderDetails("Consumerorder");

        restaurantOrderRepository.save(restaurantOrder);*/

        RestaurantOrder restaurantOrder = restaurantOrderRepository.findById(Long.valueOf(1)).get();

        log.info("Welcome to restaurant");
        return restaurantOrder;
    }
}
