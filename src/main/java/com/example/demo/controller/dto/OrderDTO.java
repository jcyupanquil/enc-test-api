package com.example.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO
{
    private Long id;
    private Long customerId;
    private String product;
    private Integer quantity;
}
