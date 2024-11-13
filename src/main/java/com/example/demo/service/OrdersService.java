package com.example.demo.service;

import com.example.demo.controller.dto.OrderDTO;

public interface OrdersService
{
    OrderDTO createOrder(OrderDTO order);

    OrderDTO getOrder(Long orderId);
}
