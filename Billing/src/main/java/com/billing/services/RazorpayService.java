package com.billing.services;

import java.util.List;

import com.billing.model.PaymentDetails;

public interface RazorpayService {
	 String createPaymentLink(Long billno) ;
	
	 List<PaymentDetails> get();
	
	 PaymentDetails getOne(Long id);

}
