package com.example.carwash.repository;

import com.example.carwash.entity.Washer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasherRepository extends JpaRepository<Washer, Long> {
    List<Washer> findAll(); 
}
