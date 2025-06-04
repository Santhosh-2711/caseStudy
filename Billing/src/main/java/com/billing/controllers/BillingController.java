package com.billing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.client.BookingClient;
import com.billing.model.Billing;
import com.billing.model.BookingDto;
import com.billing.model.SetPrices;
import com.billing.services.BillingService;
import com.billing.services.SetPricesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	private SetPricesService setPricesService;
	
	@Autowired
	private BookingClient bookingClient;
	
//	@PostMapping
//	public Billing create(@RequestBody Billing billing) {
//		
//		BookingDto booking=bookingClient.getSeating_capacity(billing.getSeating_capacity());
//		Integer seating_capacity=booking.getSeating_capacity();
//		
//		SetPrices setPrices = setPricesService.getOne(seating_capacity);
//		Integer price = setPrices.getPrice();
//		
//		
//		BookingDto booking2= bookingClient.getOrder(billing.getOrder_id());
//		
//		Integer tax=(billing.getTax() * price)/100;
//		
//		Billing billing2 = new Billing();
//		
//		billing2.setOrder_id(billing.getOrder_id());
//		billing2.setC_name(billing.getC_name());
//		billing2.setPrice(billing.getPrice());
//		billing2.setSeating_capacity(billing.getSeating_capacity());
//		billing2.setTax(billing.getTax());
//		billing2.setTotal(billing.getTotal());
//		return billingService.add(billing2);
//	}
	@PostMapping
	public Billing create(@RequestBody Billing billing) {

	    // Step 1: Fetch booking details using order_id
	    BookingDto booking = bookingClient.getOrder(billing.getOrder_id());

	    // Step 2: Extract seating_capacity from the booking
	    Integer seating_capacity = booking.getSeating_capacity();

	    // Step 3: Get price based on seating_capacity
	    SetPrices setPrices = setPricesService.getOne(seating_capacity);
	    Integer price = setPrices.getPrice();

	    // Step 4: Calculate tax
	    Integer tax = (billing.getTax() * price) / 100;

	    // Step 5: Calculate total
	    Integer total = price + tax;

	    // Step 6: Create and populate Billing object
	    Billing billing2 = new Billing();
	    billing2.setOrder_id(billing.getOrder_id());
	    billing2.setC_name(billing.getC_name());
	    billing2.setPrice(price);
	    billing2.setSeating_capacity(seating_capacity);
	    billing2.setTax(tax);
	    billing2.setTotal(total);

	    // Step 7: Save and return
	    return billingService.add(billing2);
	}

	
	
	
	@GetMapping
	public List<Billing> getAll(){
		return billingService.get();
	}
	
	@GetMapping("/{billno}")
	public Billing getAll(@PathVariable Long billno) {
		return billingService.getOne(billno);
	}
	
	@DeleteMapping("/delete/{billno}")
	public String removeBill(@PathVariable Long billno) {
		Billing bill = billingService.getOne(billno);
		billingService.delete(bill);
		return "Bill deleted Successfully";
	}


}
