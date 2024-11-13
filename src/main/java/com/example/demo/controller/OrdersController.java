package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.OrderDTO;
import com.example.demo.service.OrdersService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController
{
    private final OrdersService service;

    @PostMapping
    public OrderDTO createOrder(@RequestBody final OrderDTO order)
    {
        return service.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable Long orderId)
    {
        return service.getOrder(orderId);
    }

    @GetMapping("/test")
    public String getTest()
    {
        return "Test";
    }
}
