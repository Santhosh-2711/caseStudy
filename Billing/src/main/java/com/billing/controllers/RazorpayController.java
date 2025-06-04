package com.billing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.model.PaymentDetails;
import com.billing.services.RazorpayService;

@RestController
@RequestMapping("/payment")
public class RazorpayController {

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping("/create-payment-link/{billno}")
    public String createPaymentLink(@PathVariable Long billno) {
        return razorpayService.createPaymentLink(billno);
    }
    
    @GetMapping
	public List<PaymentDetails> getAll(){
		return razorpayService.get();
	}
	
	@GetMapping("/{id}")
	public PaymentDetails getAll(@PathVariable Long id) {
		return razorpayService.getOne(id);
	}
}
