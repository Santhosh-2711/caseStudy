package com.billing.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.billing.model.Billing;
import com.billing.repositories.BillingRepository;
import com.billing.services.BillingService;


@Service
public class BillingServiceImpl implements BillingService{
	
	private static Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);

    private BillingRepository billingRepository;
    
    

	public BillingServiceImpl(BillingRepository billingRepository) {
		super();
		this.billingRepository = billingRepository;
	}

	@Override
	public Billing add(Billing billing) {
		logger.info("Request received to add Billing: {}", billing);
        try {
            Billing savedBilling = billingRepository.save(billing);
            logger.info("Billing saved successfully: {}", savedBilling);
            return savedBilling;
        } catch (Exception e) {
            logger.error("Error occurred while adding Billing: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add Billing", e);
        }
		
		
	}

	@Override
	public List<Billing> get() {
		logger.info("Fetching all Billing entries");
        try {
            List<Billing> bills = billingRepository.findAll();
            logger.info("Fetched {} Billing entries", bills.size());
            return bills;
        } catch (Exception e) {
            logger.error("Error occurred while fetching Billing entries: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch Billing entries", e);
        }
	}

	@Override
	public Billing getOne(Long id) {
		logger.info("Fetching Billing entry with id: {}", id);
        try {
            Billing bill = billingRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill not found"));
            logger.info("Fetched Billing entry: {}", bill);
            return bill;
        } catch (Exception e) {
            logger.error("Error occurred while fetching Billing with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Bill not found", e);
        }
	}

	@Override
	public void delete(Billing billing) {
		logger.info("Request received to delete Billing: {}", billing);
        try {
            billingRepository.delete(billing);
            logger.info("Billing deleted successfully: {}", billing);
        } catch (Exception e) {
            logger.error("Error occurred while deleting Billing: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to delete Billing", e);
        }
    }
		
	

}
