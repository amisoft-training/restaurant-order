package com.springtraining.order.config;

public interface OrderConstant {

    public final static String QUEUE_NAME = "restaurantorder";
    public final static String EXCHANGE_NAME = "restaurantorder";
    public final static String ROUTING_KEY = QUEUE_NAME;
}
