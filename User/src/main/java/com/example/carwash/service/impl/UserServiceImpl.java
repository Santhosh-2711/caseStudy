package com.example.carwash.service.impl;

import com.example.carwash.entity.Admin;
import com.example.carwash.entity.Customer;
import com.example.carwash.entity.Role;
import com.example.carwash.entity.User;
import com.example.carwash.entity.Washer;
import com.example.carwash.repository.AdminRepository;
import com.example.carwash.repository.CustomerRepository;
import com.example.carwash.repository.UserRepository;
import com.example.carwash.repository.WasherRepository;
import com.example.carwash.service.UserService;


import jakarta.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class UserServiceImpl implements UserService {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private WasherRepository washerRepository;
    @Autowired private AdminRepository adminRepository;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public String registerUser(User user) {
        if (user.getRole() == null) {
            throw new RuntimeException("Role is required for user registration.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        if (user.getRole() == Role.CUSTOMER) {
            Customer customer = new Customer(user.getId(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getAge());
            customerRepository.save(customer);
        } else if (user.getRole() == Role.WASHER) {
            Washer washer = new Washer(user.getId(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getAge());
            washerRepository.save(washer);
        }else if (user.getRole() == Role.ADMIN) {
            Admin admin = new Admin(user.getId(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getAge());
            adminRepository.save(admin);
        }
        else {
            throw new RuntimeException("Invalid role");
        }
        return "User registered successfully!";
    }

    
    @Override
    public List<User> getAllUsers() {
//        return userRepository.findAll();
    	logger.info("Fetching all Users");
		try {
			List<User> userList = userRepository.findAll();
			logger.info("Fetched {} Users", userList.size());
			return userList;
		} catch (Exception e) {
			logger.error("Error occurred while fetching Users: {}", e.getMessage(), e);
			return null;
		}
    }

    @Override
    public User getUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    	logger.info("Fetching User with id: {}", id);
		try {
			User user = userRepository.findById(id).orElseThrow();
			logger.info("Fetched User: {}", user);
			return user;
		} catch (Exception e) {
			logger.error("Error occurred while fetching User with id {}: {}", id, e.getMessage(), e);
			return null;
		}
    }

    
    @Override
    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
    	logger.info("Fetching all Customers");
		try {
			List<Customer> customerList = customerRepository.findAll();
			logger.info("Fetched {} Customers", customerList.size());
			return customerList;
		} catch (Exception e) {
			logger.error("Error occurred while fetching Customers: {}", e.getMessage(), e);
			return null;
		}
		
    }

//    @Override
//    public Customer getCustomerById(Long id) {
//        return customerRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
//    }
    
    @Override
	public User getUserByUsername(String username) {
    	try {
	    return userRepository.findByUsername(username);
	    }
	catch(Exception e) {
	            throw new EntityNotFoundException("User not found: " + username);}
	}


    @Override
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer existingCustomer = getCOne(id);
        existingCustomer.setUsername(customerDetails.getUsername());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
        existingCustomer.setAddress(customerDetails.getAddress());
        existingCustomer.setAge(customerDetails.getAge());
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
//        customerRepository.deleteById(id);
    	Customer customer=getCOne(id);
    	logger.info("Request received to delete customer: {}", customer);
		try {
			customerRepository.delete(customer);
			logger.info("User deleted successfully: {}", customer);
		} catch (Exception e) {
			logger.error("Error occurred while deleting Customer: {}", e.getMessage(), e);
		}
    }

    
    @Override
    public List<Washer> getAllWashers() {
//        return washerRepository.findAll();
    	logger.info("Fetching all Washers");
		try {
			List<Washer> washerList = washerRepository.findAll();
			logger.info("Fetched {} Users", washerList.size());
			return washerList;
		} catch (Exception e) {
			logger.error("Error occurred while fetching Washers: {}", e.getMessage(), e);
			return null;
		}
    }

//    @Override
//    public Washer getWasherById(Long id) {
//        return washerRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Washer not found with id: " + id));
//    }

    @Override
    public Washer updateWasher(Long id, Washer washerDetails) {
        Washer existingWasher = getWOne(id);
        existingWasher.setUsername(washerDetails.getUsername());
        existingWasher.setPhoneNumber(washerDetails.getPhoneNumber());
        existingWasher.setAddress(washerDetails.getAddress());
        existingWasher.setAge(washerDetails.getAge());
        return washerRepository.save(existingWasher);
    }

    @Override
    public void deleteWasher(Long id) {
//        washerRepository.deleteById(id);
    	Washer washer=getWOne(id);
    	logger.info("Request received to delete washer: {}", washer);
		try {
			washerRepository.delete(washer);
			logger.info("User deleted successfully: {}", washer);
		} catch (Exception e) {
			logger.error("Error occurred while deleting Washer: {}", e.getMessage(), e);
		}
    }


	
	@Override
	public Washer getWOne(Long washer_id) {
		logger.info("Fetching Washer with id: {}", washer_id);
		try {
			Washer washer = washerRepository.findById(washer_id).orElseThrow();
			logger.info("Fetched Washer: {}", washer);
			return washer;
		} catch (Exception e) {
			logger.error("Error occurred while fetching Washer with id {}: {}", washer_id, e.getMessage(), e);
			return null;
		}
	}


	@Override
	public Customer getCOne(Long id) {
		logger.info("Fetching User with id: {}", id);
		try {
			Customer customer = customerRepository.findById(id).orElseThrow();
			logger.info("Fetched User: {}", customer);
			return customer;
		} catch (Exception e) {
			logger.error("Error occurred while fetching Customer with id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}


	

	
	



	
}
