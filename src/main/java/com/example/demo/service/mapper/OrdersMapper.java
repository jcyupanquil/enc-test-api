package com.example.demo.service.mapper;

import com.example.demo.controller.dto.OrderDTO;
import com.example.demo.repository.entity.OrderEntity;

public interface OrdersMapper
{
    OrderEntity mapDTOtoEntity(OrderDTO order);

    OrderDTO mapEntityToDTO(OrderEntity save);
}
