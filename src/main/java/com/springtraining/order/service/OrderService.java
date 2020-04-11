package com.springtraining.order.service;

import com.springtraining.order.dto.RestaurantOrderDto;
import com.springtraining.order.entity.RestaurantOrder;
import com.springtraining.order.repository.RestaurantOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    RestaurantOrderRepository restaurantOrderRepository;

    public RestaurantOrderDto getOrder(String consumerId){

        RestaurantOrder restaurantOrder = restaurantOrderRepository
                .findByCustomerId(consumerId);

        RestaurantOrderDto restaurantOrderDto = new RestaurantOrderDto();
        BeanUtils.copyProperties(restaurantOrder,restaurantOrderDto);

        return restaurantOrderDto;
    }

}
