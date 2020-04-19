package com.springtraining.order.service;

import com.springtraining.order.dto.RestaurantOrderDto;
import com.springtraining.order.entity.RestaurantOrder;
import com.springtraining.order.repository.RestaurantOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.springtraining.order.config.OrderConstant.EXCHANGE_NAME;
import static com.springtraining.order.config.OrderConstant.ROUTING_KEY;

@Service
@Slf4j
public class OrderService {

    @Autowired
    RestaurantOrderRepository restaurantOrderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    public RestaurantOrderDto createOrder(RestaurantOrderDto restaurantOrderReceived) {

        RestaurantOrder restaurantOrder = new RestaurantOrder();
        BeanUtils.copyProperties(restaurantOrderReceived, restaurantOrder);

        RestaurantOrderDto restaurantOrderDtoPostSave = new RestaurantOrderDto();
        try {
            restaurantOrderRepository.save(restaurantOrder);
            BeanUtils.copyProperties(restaurantOrder, restaurantOrderDtoPostSave);
            restaurantOrderDtoPostSave.setMessage("Order saved successfully");

            log.info("Pushing create order event");
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY,restaurantOrderDtoPostSave);

        } catch (Exception ex) {
            log.error("****  Unable to save the order : {}", ex.getMessage());
            restaurantOrderDtoPostSave.setMessage(ex.getMessage());
            restaurantOrderDtoPostSave.setFlag(Boolean.FALSE);
        }

        return restaurantOrderDtoPostSave;
    }
}
