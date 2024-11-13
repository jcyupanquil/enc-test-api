package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.controller.dto.OrderDTO;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.entity.OrderEntity;
import com.example.demo.service.OrdersService;
import com.example.demo.service.mapper.OrdersMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService
{

    private final OrdersRepository repository;
    private final OrdersMapper     mapper;

    @Override
    public OrderDTO createOrder(OrderDTO order)
    {
        OrderEntity orderEntity = mapper.mapDTOtoEntity(order);
        return mapper.mapEntityToDTO(repository.save(orderEntity));
    }

    @Override
    public OrderDTO getOrder(Long orderId)
    {
        OrderEntity entity = repository.findById(orderId)
                .orElseThrow(NullPointerException::new);
        return mapper.mapEntityToDTO(entity);
    }
}
