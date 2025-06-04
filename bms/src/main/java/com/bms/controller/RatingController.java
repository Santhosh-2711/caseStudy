package com.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bms.model.Ratingreview;
import com.bms.service.RatingService;

@RestController
@RequestMapping("/feedback")
public class RatingController {
	@Autowired
	RatingService ratingService;
	
	@PostMapping("/customer/post")
	public Ratingreview postfeedback(@RequestBody Ratingreview rr) {
		return ratingService.add(rr);
	}
	
	@GetMapping("/admin/{id}")
		public Ratingreview getFeedbackdetails(@PathVariable Long id){
			return ratingService.getOne(id);
		
		
	}

}
