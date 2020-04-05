package com.springtraining.order.repository;

import com.springtraining.order.entity.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder,Long> {
}
