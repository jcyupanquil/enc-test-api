package com.example.demo.service.mapper.impl;

import org.springframework.stereotype.Component;

import com.example.demo.controller.dto.OrderDTO;
import com.example.demo.repository.entity.OrderEntity;
import com.example.demo.service.mapper.OrdersMapper;

@Component
public class OrdersMapperImpl implements OrdersMapper
{
    @Override
    public OrderEntity mapDTOtoEntity(OrderDTO order)
    {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProduct(order.getProduct());
        entity.setQuantity(order.getQuantity());
        return entity;
    }

    @Override
    public OrderDTO mapEntityToDTO(OrderEntity entity)
    {
        OrderDTO order = new OrderDTO();
        order.setId(entity.getOrderId());
        order.setCustomerId(entity.getCustomerId());
        order.setProduct(entity.getProduct());
        order.setQuantity(entity.getQuantity());
        return order;
    }
}
