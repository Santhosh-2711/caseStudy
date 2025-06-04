package com.bms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Bookings {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long order_id;
	private String c_name;
	private long c_phno;
	private int seating_capacity;
	private String c_location;
	private String schedule_time;
	private long userId;
	private long washerId;
	private String orderstatus;
	private String washstatus;
	
	public Bookings() {
		super();
	}

	public Bookings(long order_id, String c_name, long c_phno, int seating_capacity, String c_location,
			String schedule_time, long user_id, long washer_id, String status) {
		super();
		this.order_id = order_id;
		this.c_name = c_name;
		this.c_phno = c_phno;
		this.seating_capacity = seating_capacity;
		this.c_location = c_location;
		this.schedule_time = schedule_time;
		this.userId = user_id;
		this.washerId =washer_id;
		this.orderstatus = orderstatus;
		this.washstatus= washstatus;
	}
	
	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public long getC_phno() {
		return c_phno;
	}

	public void setC_phno(long c_phno) {
		this.c_phno = c_phno;
	}

	public int getSeating_capacity() {
		return seating_capacity;
	}

	public void setSeating_capacity(int seating_capacity) {
		this.seating_capacity = seating_capacity;
	}

	public String getC_location() {
		return c_location;
	}

	public void setC_location(String c_location) {
		this.c_location = c_location;
	}

	public String getSchedule_time() {
		return schedule_time;
	}

	public void setSchedule_time(String schedule_time) {
		this.schedule_time = schedule_time;
	}

	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getWasherId() {
		return washerId;
	}

	public void setWasherId(long washerId) {
		this.washerId = washerId;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getWashstatus() {
		return washstatus;
	}

	public void setWashstatus(String washstatus) {
		this.washstatus = washstatus;
	}

	
	

	

	
}
