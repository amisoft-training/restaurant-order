package com.springtraining.order.service;

import com.springtraining.order.dto.RestaurantOrderDto;
import com.springtraining.order.entity.RestaurantOrder;
import com.springtraining.order.repository.RestaurantOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    RestaurantOrderRepository restaurantOrderRepository;

    public RestaurantOrderDto getOrder(String consumerId){

        RestaurantOrderDto restaurantOrderDto = new RestaurantOrderDto();
        Optional<RestaurantOrder> restaurantOrderOptional = restaurantOrderRepository
                .findByCustomerId(consumerId);

        if(restaurantOrderOptional.isPresent()){
            BeanUtils.copyProperties(restaurantOrderOptional.get(),restaurantOrderDto);
            restaurantOrderDto.setMessage("Success find the order");
        }else{
            restaurantOrderDto.setMessage("No order find for the consumerID ");
        }
        return restaurantOrderDto;
    }

}
