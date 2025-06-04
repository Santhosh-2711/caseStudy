package com.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.model.Bookings;

@Repository
public interface BmsRepo extends JpaRepository<Bookings, Long>{
	List<Bookings> findByOrderstatus(String washstatus);
	List<Bookings> findByUserId(Long userId);


}
