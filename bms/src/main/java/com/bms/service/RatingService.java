package com.bms.service;



import com.bms.model.Ratingreview;

public interface RatingService {
	Ratingreview add(Ratingreview rr);
	Ratingreview getOne(Long orderId);

}
