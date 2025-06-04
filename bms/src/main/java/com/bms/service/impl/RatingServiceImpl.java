package com.bms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.model.Bookings;
import com.bms.model.Ratingreview;
import com.bms.repository.RatingRepo;
import com.bms.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
    
	
	private static Logger logger= LoggerFactory.getLogger(RatingServiceImpl.class);
	
	@Autowired
	RatingRepo ratingRepo;
	
	
	
	
	public RatingServiceImpl(RatingRepo ratingRepo) {
		super();
		this.ratingRepo = ratingRepo;
	}

	@Override
	public Ratingreview add(Ratingreview rr) {
		logger.info("Rated the car wash service successfully {}",rr);
		try {
		Ratingreview savedRating= ratingRepo.save(rr);
		logger.info("Rated the car wash service successfully {}",rr);
		return savedRating;
		}catch(Exception e) {
			logger.error("Error while rating the service: {}", e.getMessage(),e);
			return null;
		}
	}

	@Override
	public Ratingreview getOne(Long orderId) {
		logger.info("fetching Rating and Review with Order id : {}",orderId);
		try {
			Ratingreview rr=ratingRepo.findById(orderId).orElseThrow();
			logger.info("Fetched Rating and Review : {}",rr);
			return rr;
		}catch (Exception e) {
			logger.error("Error occurred while fetching Rating and Review with order id {}", orderId, e.getMessage(), e);
			return null;
		}
		
	}
}


