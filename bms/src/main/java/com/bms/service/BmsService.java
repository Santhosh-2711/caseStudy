package com.bms.service;

import java.util.List;

import com.bms.model.Bookings;



public interface BmsService {

	Bookings add(Bookings order);
	List<Bookings> get();
	Bookings getOne(Long order_id);
	List<Bookings> getall(long user_id);
	void cancel(Bookings order);
}
