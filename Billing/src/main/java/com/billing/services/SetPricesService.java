package com.billing.services;

import java.util.List;

import com.billing.model.SetPrices;

public interface SetPricesService {
	
	SetPrices add(SetPrices setPrices);
	
	List<SetPrices> get();
	
	SetPrices getOne(Integer seating_capacity);
	
	void delete(SetPrices setPrices);

}
