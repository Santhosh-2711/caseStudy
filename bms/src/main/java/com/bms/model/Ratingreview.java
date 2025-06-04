package com.bms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ratingreview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rating_id;
	
	
	private int Rating;
	private String Review;
	private Long orderId;
	
	
	public Ratingreview(Long rating_id,Long orderId, int rating, String review) {
		super();
		this.orderId = orderId;
		this.rating_id=rating_id;
		Rating = rating;
		Review = review;
	}
	

	public Ratingreview() {
		super();
	}

	

	public Long getRating_id() {
		return rating_id;
	}


	public void setRating_id(Long rating_id) {
		this.rating_id = rating_id;
	}


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public String getReview() {
		return Review;
	}

	public void setReview(String review) {
		Review = review;
	}
	
	
	

}
