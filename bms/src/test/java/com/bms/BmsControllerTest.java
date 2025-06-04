package com.bms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bms.controller.BmsController;
import com.bms.model.Bookings;
import com.bms.service.BmsService;

@ExtendWith(MockitoExtension.class)
public class BmsControllerTest {

    @Mock
    private BmsService bmsService;

    @InjectMocks
    private BmsController bmsController;

    @Test
    public void testCreate() {
        Bookings order = new Bookings();
        when(bmsService.add(any(Bookings.class))).thenReturn(order);

        Bookings createdOrder = bmsController.create(order);

        assertNotNull(createdOrder);
    }

    @Test
    public void testGet() {
        when(bmsService.get()).thenReturn(List.of(new Bookings()));

        List<Bookings> orders = bmsController.get();

        assertFalse(orders.isEmpty());
    }

    @Test
    public void testGetDetails() {
        Bookings order = new Bookings();
        when(bmsService.getOne(anyLong())).thenReturn(order);

        Bookings fetchedOrder = bmsController.getDetails(1L);

        assertNotNull(fetchedOrder);
    }

    @Test
    public void testRemoveUser() {
        Bookings order = new Bookings();
        when(bmsService.getOne(anyLong())).thenReturn(order);
        doNothing().when(bmsService).cancel(order);

        String response = bmsController.removeUser(1L);

        assertEquals("Order Cancelled Successfully", response);
    }
}