package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.model.Bookings;
import com.bms.service.BmsService;




@RestController
@RequestMapping("/order")
public class BmsController {
	
	@Autowired
	private BmsService bmsService;
	
	@PostMapping("/customer/request")
	public Bookings create(@RequestBody Bookings order) {
		return bmsService.add(order);
	}
	
	@GetMapping("/washer/pending")
	public List<Bookings> get(){
		return bmsService.get();
	}
	
	@PutMapping("/washer/{id}")
	public Bookings updateOrder(@PathVariable Long id,@RequestBody Bookings order) {
		Bookings change =  bmsService.getOne(id);
		change.setOrderstatus(order.getOrderstatus());
		change.setWasherId(order.getWasherId());
		change.setWashstatus(order.getWashstatus());
		return bmsService.add(change);
	}
	@GetMapping("/admin/{id}")
	public Bookings getDetails(@PathVariable Long id){
		return bmsService.getOne(id);
	}
	@GetMapping("/customer/{id}")
	public List<Bookings> getAll(@PathVariable Long id){
		return bmsService.getall(id);
	}
	@DeleteMapping("customer/cancel/{id}")
	public String removeUser(@PathVariable Long id) {
		Bookings order = bmsService.getOne(id);
	    bmsService.cancel(order);
	    return "Order Cancelled Successfully";
	}
	

}
