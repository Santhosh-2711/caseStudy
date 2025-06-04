package com.billing.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.billing.model.Billing;
import com.billing.model.PaymentDetails;
import com.billing.repositories.PaymentDetailsRepository;
import com.billing.services.BillingService;
import com.billing.services.RazorpayService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;

@Service
public class RazorpayServiceImpl implements RazorpayService{
	
	 	@Value("${razorpay.api.key}")
	    private String razorpayApiKey;

	    @Value("${razorpay.api.secret}")
	    private String razorpayApiSecret;
	    
	    @Autowired
	    private BillingService billingService;

	    @Autowired
	    private PaymentDetailsRepository paymentDetailsRepository;

	@Override
	public String createPaymentLink(Long billno) {
		try {
            Billing billing = billingService.getOne(billno);
            if (billing == null) {
                throw new RuntimeException("Billing with ID " + billno + " not found.");
            }

            RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSecret);

            double amountInPaise = billing.getTotal() * 100;
            
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amountInPaise);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("description", "Payment for Client Name - " + billing.getC_name());
            
            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

            String paymentLinkUrl = paymentLink.get("short_url");

            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setBillno(billno);
            paymentDetails.setTotalpaid((double) billing.getTotal());
            paymentDetails.setPaymentStatus("Pending");
            paymentDetails.setPaymentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            paymentDetails.setTransactionId(paymentLink.get("id"));
            paymentDetailsRepository.save(paymentDetails);
            return paymentLinkUrl;
	}catch (Exception e) {
        throw new RuntimeException("Failed to create Razorpay payment link: " + e.getMessage(), e);
    }
	}

	@Override
	public List<PaymentDetails> get() {
		try {
            return paymentDetailsRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch payment details", e);
        }
	}

	@Override
	public PaymentDetails getOne(Long id) {
		try {
            return paymentDetailsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Payment details not found for ID " + id));
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch payment details: " + e.getMessage(), e);
        }
	}

}
