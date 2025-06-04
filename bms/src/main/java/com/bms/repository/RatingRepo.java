package com.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.model.Ratingreview;



@Repository
public interface RatingRepo extends JpaRepository<Ratingreview, Long>{
	
}
