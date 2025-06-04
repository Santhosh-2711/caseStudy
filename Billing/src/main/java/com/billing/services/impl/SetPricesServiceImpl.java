package com.billing.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.billing.model.SetPrices;
import com.billing.repositories.SetPricesRepository;
import com.billing.services.SetPricesService;

@Service
public class SetPricesServiceImpl implements SetPricesService{
	
	private static Logger logger = LoggerFactory.getLogger(SetPricesServiceImpl.class);

    private SetPricesRepository setPricesRepository;

    public SetPricesServiceImpl(SetPricesRepository setPricesRepository) {
        this.setPricesRepository = setPricesRepository;
    }

	@Override
	public SetPrices add(SetPrices setPrices) {
		logger.info("Request received to add SetPrices: {}", setPrices);
        try {
            SetPrices savedSetPrices = setPricesRepository.save(setPrices);
            logger.info("SetPrices saved successfully: {}", savedSetPrices);
            return savedSetPrices;
        } catch (Exception e) {
            logger.error("Error occurred while adding SetPrices: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add SetPrices", e);
        }
	}

	@Override
	public List<SetPrices> get() {
		logger.info("Fetching all SetPrices entries");
        try {
            List<SetPrices> prices = setPricesRepository.findAll();
            logger.info("Fetched {} SetPrices entries", prices.size());
            return prices;
        } catch (Exception e) {
            logger.error("Error occurred while fetching SetPrices entries: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch SetPrices entries", e);
        }
	}

	@Override
	public SetPrices getOne(Integer seating_capacity) {
		logger.info("Fetching SetPrices entry with seating_capacity: {}",seating_capacity );
        try {
            SetPrices price = setPricesRepository.findById(seating_capacity).orElseThrow(() -> new RuntimeException("Price not found"));
            logger.info("Fetched SetPrices entry: {}", price);
            return price;
        } catch (Exception e) {
            logger.error("Error occurred while fetching SetPrices with seating_capacity {}: {}", seating_capacity, e.getMessage(), e);
            throw new RuntimeException("Price not found", e);
        }
	}

	@Override
	public void delete(SetPrices setPrices) {
		logger.info("Request received to delete SetPrices: {}", setPrices);
        try {
            setPricesRepository.delete(setPrices);
            logger.info("SetPrices deleted successfully: {}", setPrices);
        } catch (Exception e) {
            logger.error("Error occurred while deleting SetPrices: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to delete SetPrices", e);
        }
		
	}

}
