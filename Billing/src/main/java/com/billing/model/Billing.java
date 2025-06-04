package com.billing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Billing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long billno;
	private Long order_id;
	private String c_name;
	private Integer seating_capacity;
	private Integer price;
	private Integer tax;
	private Integer total;
	public Billing() {
		super();
	}
	public Billing(Long billno, Long bookId, Integer seating_capacity, Integer price, Integer tax, Integer total) {
		super();
		this.billno = billno;
		this.order_id = order_id;
		this.c_name=c_name;
		this.seating_capacity = seating_capacity;
		this.price = price;
		this.tax = tax;
		this.total = total;
	}
	public Long getBillno() {
		return billno;
	}
	public void setBillno(Long billno) {
		this.billno = billno;
	}
	
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Integer getSeating_capacity() {
		return seating_capacity;
	}
	public void setSeating_capacity(Integer seating_capacity) {
		this.seating_capacity = seating_capacity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	
	

}
