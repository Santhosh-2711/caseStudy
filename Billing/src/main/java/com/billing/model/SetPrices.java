package com.billing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SetPrices {
	@Id
	private Integer seating_capcity;
	private Integer price;
	public SetPrices() {
		super();
	}
	public SetPrices(Integer seating_capcity, Integer price) {
		super();
		this.seating_capcity = seating_capcity;
		this.price = price;
	}
	public Integer getSeating_capcity() {
		return seating_capcity;
	}
	public void setSeating_capcity(Integer seating_capcity) {
		this.seating_capcity = seating_capcity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	

}
