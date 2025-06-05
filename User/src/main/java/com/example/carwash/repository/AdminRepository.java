package com.example.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carwash.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
