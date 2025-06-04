package com.bms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bms.model.Bookings;
import com.bms.repository.BmsRepo;
import com.bms.service.impl.BmsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BmsServiceImplTest {

    @Mock
    private BmsRepo bmsRepo;

    @InjectMocks
    private BmsServiceImpl bmsService;

    @Test
    public void testAdd() {
        Bookings order = new Bookings();
        order.setOrderstatus("pending");
        order.setWashstatus("pending");

        when(bmsRepo.save(any(Bookings.class))).thenReturn(order);

        Bookings savedOrder = bmsService.add(order);

        assertNotNull(savedOrder);
        assertEquals("pending", savedOrder.getOrderstatus());
        assertEquals("pending", savedOrder.getWashstatus());
    }

    @Test
    public void testGet() {
        when(bmsRepo.findByOrderstatus("pending")).thenReturn(List.of(new Bookings()));

        List<Bookings> orders = bmsService.get();

        assertFalse(orders.isEmpty());
    }

    @Test
    public void testGetOne() {
        Bookings order = new Bookings();
        when(bmsRepo.findById(anyLong())).thenReturn(Optional.of(order));

        Bookings fetchedOrder = bmsService.getOne(1L);

        assertNotNull(fetchedOrder);
    }

    @Test
    public void testCancel() {
        Bookings order = new Bookings();
        doNothing().when(bmsRepo).delete(order);

        bmsService.cancel(order);

        verify(bmsRepo, times(1)).delete(order);
    }
}