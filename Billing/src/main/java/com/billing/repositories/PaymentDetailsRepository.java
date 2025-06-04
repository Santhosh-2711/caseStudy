package com.billing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billing.model.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails,Long>{

}
