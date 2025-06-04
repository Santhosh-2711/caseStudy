package com.bms.service.impl;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.model.Bookings;
import com.bms.repository.BmsRepo;
import com.bms.service.BmsService;

@Service 
public class BmsServiceImpl implements BmsService {
	
	private static Logger logger= LoggerFactory.getLogger(BmsServiceImpl.class);
	
	@Autowired 
	BmsRepo bmsRepo;
	
	
	public BmsServiceImpl(BmsRepo bmsRepo) {
		super();
		this.bmsRepo = bmsRepo;
	}

	@Override
	public Bookings add(Bookings order) {
		logger.info("Car Wash Order Placed Successfully {}",order);
		
		try {
			if(order.getOrderstatus() == null) {
	            order.setOrderstatus("pending");
	        }
	        if(order.getWashstatus() == null) {
	            order.setWashstatus("pending");
	        }

			Bookings savedOrder = bmsRepo.save(order);
			logger.info("Car Wash Order Placed Successfully : {}",savedOrder);
			return savedOrder;
		}catch(Exception e) {
			logger.error("Error occurred while requesting car wash: {}", e.getMessage(), e);
			return null;
		}
		
		
	}

	@Override
	public List<Bookings> get() {
		logger.info("Fetching all Pending Orders");
		try {
			List<Bookings> orderList=bmsRepo.findByOrderstatus("pending");
			return orderList;
		}catch(Exception e) {
			logger.error("Error while fetching Pending Orders: {}", e.getMessage(),e);
			return null;
		}
		
	}

	@Override
	public Bookings getOne(Long order_id) {
		logger.info("fetching Order Details with Order id : {}",order_id);
		try {
			Bookings order=bmsRepo.findById(order_id).orElseThrow();
			logger.info("Fetched Order Details: {}",order);
			return order;
		}catch (Exception e) {
			logger.error("Error occurred while fetching Order Details with order id {}", order_id, e.getMessage(), e);
			return null;
		}
		
	}

	@Override
	public List<Bookings> getall(long user_id) {
		logger.info("Fetching all orders with User id: {}",user_id);
		try {
			List<Bookings> orderList=bmsRepo.findByUserId(user_id);
			 return orderList;
		}catch(Exception e) {
			logger.error("Error occurred while fetching all Orders with user id {}", user_id, e.getMessage(), e);
			return null;
		}
		
	}

	@Override
	public void cancel(Bookings order) {
		logger.info("Request received to cancel order: {}", order);
		try {
			bmsRepo.delete(order);
			logger.info("order cancelled successfully: {}", order);
			}catch (Exception e) {
				logger.error("Error occurred while cancelling order: {}", e.getMessage(), e);
			}
		
		
	}

}
