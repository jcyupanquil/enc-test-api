package com.example.demo.service.impl;

import java.util.Optional;

import com.example.demo.controller.dto.OrderDTO;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.entity.OrderEntity;
import com.example.demo.service.mapper.OrdersMapper;
import com.example.demo.util.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceImplTest
{

    @Mock
    private OrdersRepository repository;
    @Mock
    private OrdersMapper     mapper;

    @InjectMocks
    private OrdersServiceImpl service;

    private OrderDTO    mockOrderDTO;
    private OrderEntity mockOrderEntity;

    @BeforeEach
    void setUp()
    {
        // Inicializando mock data, también podríamos usar archivos JSON almacenados en el folder "test/resources".
        // En caso tuviéramos más tests, dependiendo del caso y lo que se quiera testear,
        // se podría instanciar el mock data al inicio de cada método.
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
    void testCreateOrderWhenPersistingOrderThenReturnsDTO()
    {
        when(mapper.mapDTOtoEntity(any(OrderDTO.class))).thenReturn(mockOrderEntity);
        // Mockeamos el método "save" para que no se haga la llamada a la base de datos
        // pero sí verificaremos que se llame al método "save" 
        when(repository.save(any(OrderEntity.class))).thenReturn(mockOrderEntity);
        when(mapper.mapEntityToDTO(any(OrderEntity.class))).thenReturn(mockOrderDTO);

        OrderDTO result = service.createOrder(mockOrderDTO);

        verify(repository).save(mockOrderEntity);
        assertNotNull(result);
        assertEquals(mockOrderDTO.getId(), result.getId());
        assertEquals(mockOrderDTO.getCustomerId(), result.getCustomerId());
        assertEquals(mockOrderDTO.getProduct(), result.getProduct());
        assertEquals(mockOrderDTO.getQuantity(), result.getQuantity());
    }

    @Test
    void testGetOrderWhenOrderExistsThenReturnsOrderDTO()
    {
        when(repository.findById(anyLong())).thenReturn(Optional.of(mockOrderEntity));
        when(mapper.mapEntityToDTO(mockOrderEntity)).thenReturn(mockOrderDTO);

        OrderDTO result = service.getOrder(1L);

        verify(repository).findById(1L);
        assertNotNull(result);
        assertEquals(mockOrderDTO.getId(), result.getId());
        assertEquals(mockOrderDTO.getCustomerId(), result.getCustomerId());
        assertEquals(mockOrderDTO.getProduct(), result.getProduct());
        assertEquals(mockOrderDTO.getQuantity(), result.getQuantity());
    }

    @Test
    void testGetOrderWhenOrderDoesNotExistThenThrowsException()
    {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getOrder(1L));

        verify(repository).findById(1L);
    }
}