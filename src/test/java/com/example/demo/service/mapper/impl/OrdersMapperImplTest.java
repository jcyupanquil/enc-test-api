package com.example.demo.service.mapper.impl;

import com.example.demo.controller.dto.OrderDTO;
import com.example.demo.repository.entity.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

// No injectamos dependencias por lo que no se necesita mockito, con JUnit/Jupiter es suficiente
public class OrdersMapperImplTest
{

    private OrdersMapperImpl mapper;
    private OrderDTO         mockOrderDTO;
    private OrderEntity      mockOrderEntity;

    @BeforeEach
    void setUp()
    {
        mapper = new OrdersMapperImpl();

        mockOrderDTO = new OrderDTO();
        mockOrderDTO.setId(1L);
        mockOrderDTO.setCustomerId(100L);
        mockOrderDTO.setProduct("Product A");
        mockOrderDTO.setQuantity(10);

        mockOrderEntity = new OrderEntity();
        mockOrderEntity.setOrderId(2L);
        mockOrderEntity.setCustomerId(200L);
        mockOrderEntity.setProduct("Product B");
        mockOrderEntity.setQuantity(20);
    }

    @Test
    void testMapDTOtoEntityWhenFullDTOThenReturnsFullEntityWithoutId()
    {
        OrderEntity entity = mapper.mapDTOtoEntity(mockOrderDTO);

        assertNotNull(entity);
        assertEquals(mockOrderDTO.getCustomerId(), entity.getCustomerId());
        assertEquals(mockOrderDTO.getProduct(), entity.getProduct());
        assertEquals(mockOrderDTO.getQuantity(), entity.getQuantity());
        // El ID no se mapea, para forzar que sea siempre autogenerado
        assertNull(entity.getOrderId());
    }

    @Test
    void testMapEntityToDTOWhenFullEntityThenReturnsFullDTO()
    {
        OrderDTO dto = mapper.mapEntityToDTO(mockOrderEntity);

        assertNotNull(dto);
        assertEquals(mockOrderEntity.getOrderId(), dto.getId());
        assertEquals(mockOrderEntity.getCustomerId(), dto.getCustomerId());
        assertEquals(mockOrderEntity.getProduct(), dto.getProduct());
        assertEquals(mockOrderEntity.getQuantity(), dto.getQuantity());
    }

}
