package com.billing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.billing.model.BookingDto;


@FeignClient(name = "booking-review")
public  interface BookingClient {
	@GetMapping("/booking/{id}")
	
	BookingDto getOrder(@PathVariable("id") Long order_id);

//	BookingDto getSeating_capacity(Integer seating_capacity);

//	BookingDto getSeating_capacity(Integer seating_capacity);

	
		

}
