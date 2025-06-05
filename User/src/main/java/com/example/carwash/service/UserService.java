package com.example.carwash.service;

import com.example.carwash.entity.Customer;
import com.example.carwash.entity.User;
import com.example.carwash.entity.Washer;

import java.util.List;
import java.util.Optional;

public interface UserService {
    
    String registerUser(User user);

   
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByUsername(String username);
    

        List<Customer> getAllCustomers();
//    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, Customer customerDetails);
    void deleteCustomer(Long id);
    Customer getCOne(Long washer_id);
    
    List<Washer> getAllWashers();
//    Washer getWasherById(Long id);
    Washer getWOne(Long washer_id);
    Washer updateWasher(Long id, Washer washerDetails);
    void deleteWasher(Long id);
    
}
