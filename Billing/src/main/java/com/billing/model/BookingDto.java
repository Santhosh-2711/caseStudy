package com.billing.model;




public class BookingDto {
	private Long order_id;
	private Integer seating_capacity;

	public BookingDto() {
		super();
	}

	public BookingDto(Integer seating_capacity) {
		super();
		this.seating_capacity = seating_capacity;
		this.order_id=order_id;
	}

	public Integer getSeating_capacity() {
		return seating_capacity;
	}

	public void setSeating_capacity(Integer seating_capacity) {
		this.seating_capacity = seating_capacity;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	

}
