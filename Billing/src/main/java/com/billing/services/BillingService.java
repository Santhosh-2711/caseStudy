package com.billing.services;

import java.util.List;

import com.billing.model.Billing;

public interface BillingService {
	
	Billing add(Billing billing);
	
	List<Billing> get();
	
	Billing getOne(Long id);
	
	void delete(Billing billing);

}
