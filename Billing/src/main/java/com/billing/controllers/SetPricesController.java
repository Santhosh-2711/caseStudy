package com.billing.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.model.SetPrices;
import com.billing.services.SetPricesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/setprices")
public class SetPricesController {
	
	private SetPricesService setPricesService;
	
	public SetPricesController(SetPricesService setPricesService) {
		super();
		this.setPricesService = setPricesService;
	}

	@PostMapping
	public SetPrices create(@RequestBody SetPrices setPrices) {
		return setPricesService.add(setPrices);
	}
	
	@GetMapping
	public List<SetPrices> getAll(){
		return setPricesService.get();
	}
	
	@GetMapping("/{seating_capacity}" )
	public SetPrices getAll(@PathVariable Integer seating_capacity) {
		return setPricesService.getOne(seating_capacity);
	}
	
	@PutMapping("/{seating_capacity}")
	public SetPrices updatePrices(@PathVariable Integer seating_capacity,@RequestBody SetPrices setPrices) {
		SetPrices change =  setPricesService.getOne(seating_capacity);
		
		change.setPrice(setPrices.getPrice());
		return setPricesService.add(change);
		
	}
	
	@DeleteMapping("delete/{seating_capacity}")
	public String removePrice(@PathVariable Integer seating_capacity) {
		SetPrices price = setPricesService.getOne(seating_capacity);
	    setPricesService.delete(price);
	    return "Price deleted Successfully";
	}
	

}
